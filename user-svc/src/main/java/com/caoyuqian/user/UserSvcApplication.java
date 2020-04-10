package com.caoyuqian.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lumiaqian
 */
@SpringBootApplication
@MapperScan("com.caoyuqian.user.mapper")
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.caoyuqian.common","com.caoyuqian.user"})
public class UserSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSvcApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
