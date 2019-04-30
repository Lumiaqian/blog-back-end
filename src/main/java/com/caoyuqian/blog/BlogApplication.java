package com.caoyuqian.blog;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

@SpringBootApplication
@EnableCaching
@MapperScan("com.caoyuqian.blog.mapper")
public class BlogApplication {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个converter 转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(                //保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的NULL转化为""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的NULL转化为0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的NULL转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的NULL转化为false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
        // 3、在convert 中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        // 4、将convert 添加到converters当中
        HttpMessageConverter<?> converter = fastConverter;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return new HttpMessageConverters(converter);
    }

    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BlogApplication.class, args);
    }




}
