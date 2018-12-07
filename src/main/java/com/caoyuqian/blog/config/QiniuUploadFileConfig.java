package com.caoyuqian.blog.config;

import com.caoyuqian.blog.pojo.ConstantQiniu;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qian
 * @version V1.0
 * @Title: QiniuUploadFileConfig
 * @Package: com.caoyuqian.blog.config
 * @Description: TOTO
 * @date 2018-12-07 11:17
 **/
@Configuration
public class QiniuUploadFileConfig {
    /**
     * 华南机房,配置自己空间所在的区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Zone.zone2());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    @Bean
    @ConfigurationProperties(prefix = "qiniu")
    public ConstantQiniu constantQiniu(){
        return new ConstantQiniu();
    }

    /**
     * 认证信息实例
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(constantQiniu().getAccessKey(), constantQiniu().getSecretKey());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }

}
