package com.caoyuqian.gatewaysvc;

import com.caoyuqian.gatewaysvc.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySvcApplication.class, args);
    }

    /**
     * 自定义 token 过滤器
     *//*
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }*/

}
