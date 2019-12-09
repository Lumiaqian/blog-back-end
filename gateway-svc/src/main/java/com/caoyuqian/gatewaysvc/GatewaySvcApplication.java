package com.caoyuqian.gatewaysvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySvcApplication.class, args);
    }

}
