package com.caoyuqian.blogsvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@MapperScan("com.caoyuqian.blogsvc.mapper")
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.caoyuqian.common","com.caoyuqian.blogsvc"})
public class BlogSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSvcApplication.class, args);
    }

}
