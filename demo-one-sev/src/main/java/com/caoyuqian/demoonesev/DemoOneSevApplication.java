package com.caoyuqian.demoonesev;

import com.caoyuqian.commom.api.Result;
import com.caoyuqian.user.client.UserClient;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.caoyuqian.user"})
public class DemoOneSevApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOneSevApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @Autowired
        UserClient userClient;

        @GetMapping("/test")
        public Result test() {
            String mobile = "18827925852";
            String password = "a1b2c3d4";
            VerifyPasswordRequest request = VerifyPasswordRequest.builder()
                    .mobile(mobile)
                    .password(password)
                    .build();

            return userClient.verifyPassword(request);
        }
    }


}
