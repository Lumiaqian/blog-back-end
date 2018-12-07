package com.caoyuqian.blog.service;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

public interface IQiniuUploadFileService {
    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream,String key) throws QiniuException;

    String uploadImg(InputStream inputStream,String key) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
