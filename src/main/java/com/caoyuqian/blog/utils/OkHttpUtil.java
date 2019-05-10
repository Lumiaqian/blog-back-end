package com.caoyuqian.blog.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: OkHttpUtil
 * @Package: com.caoyuqian.blog.utils
 * @Description: OkHttp工具类
 * @date 2018/8/11 下午9:42
 **/
public class OkHttpUtil {
    private static final Logger logger=LoggerFactory.getLogger(OkHttpUtil.class);

     /**
       * @Param: url querirs
       * @return:
       * @Author: qian
       * @Description: get请求
       * @Date: 2018/8/11 下午10:02
      **/
    public static String get(String url, Map<String,String> queries){
        String responseBody="";
        StringBuffer sb=new StringBuffer(url);
        if (queries !=null && queries.keySet().size()>0){
            boolean firstFlag=true;
            Iterator iterator=queries.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry=(Map.Entry<String,String>) iterator.next();
                if (firstFlag){
                    sb.append("?"+entry.getKey()+"="+entry.getValue());
                    firstFlag=false;
                }else {
                    sb.append("&"+entry.getKey()+"="+entry.getValue());
                }
            }
        }
        Request request=new Request.Builder().url(sb.toString()).build();
        logger.info("url: "+sb.toString());
        Response response=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            response=okHttpClient.newCall(request).execute();
            int status=response.code();
            logger.info("status: "+status);
            if (response.isSuccessful()){
                return JSONUtil.unicodeToString(response.body().string());
            }

        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", e.getStackTrace());
        } finally {
            if (response !=null){
                response.close();
            }
        }

        return responseBody;
    }

     /**
       * @Param: url params
       * @return:
       * @Author: qian
       * @Description: post form 提交数据
       * @Date: 2018/8/11 下午10:05
      **/
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", e.getStackTrace().toString());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
    public static String put(String url, MultipartFile file) throws IOException {
        String responseBody = "";
        // form 表单形式上传,MultipartBody的内容类型是表单格式，multipart/form-data
        MultipartBody.Builder builder= new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数
        builder.addFormDataPart("file",file.getOriginalFilename(),
                RequestBody.create(MediaType.parse("multipart/form-data"), file.getBytes())
        );
        Request request = new Request.Builder()
                .url(url)
                .put(builder.build())
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", e.getStackTrace().toString());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
     /**
       * @Param: url queries
       * @return: 
       * @Author: qian
       * @Description: getForHeader
       * @Date: 2018/8/11 下午10:07
      **/
    public static String getForHeader(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

     /**
       * @Param: url json
       * @return:
       * @Author: qian
       * @Description: post请求json数据
       * @Date: 2018/8/11 下午10:08
      **/
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", e.getStackTrace());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }
}
