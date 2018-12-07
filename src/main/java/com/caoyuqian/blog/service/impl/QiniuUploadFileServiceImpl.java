package com.caoyuqian.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.ConstantQiniu;
import com.caoyuqian.blog.service.IQiniuUploadFileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * @author qian
 * @version V1.0
 * @Title: QiniuUploadFileServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018-12-07 11:24
 **/
@Service
public class QiniuUploadFileServiceImpl implements IQiniuUploadFileService, InitializingBean {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Autowired
    private ConstantQiniu constantQiniu;

    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, file.getName(), getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public Response uploadFile(InputStream inputStream,String key) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }

    @Override
    public String uploadImg(InputStream inputStream, String key) throws QiniuException {
        Response response = uploadFile(inputStream,key);
        JSONObject jsonObject = JSON.parseObject(response.bodyString());
        String url = constantQiniu.getPath()+ jsonObject.get("key");
        return url;
    }

    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(constantQiniu.getBucket(), key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(constantQiniu.getBucket(), key);
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    private String getUploadToken() {
        return this.auth.uploadToken(constantQiniu.getBucket(), null, 3600, putPolicy);
    }
}
