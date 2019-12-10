package com.caoyuqian.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.caoyuqian.auth.mapper")
@EnableFeignClients(basePackages = {"com.caoyuqian.user"})
@ComponentScan(basePackages = {"com.caoyuqian.common","com.caoyuqian.auth"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
