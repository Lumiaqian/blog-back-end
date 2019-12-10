package com.caoyuqian.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author qian
 * @version V1.0
 * @Title: ResourceServerConfig
 * @Package: com.caoyuqian.auth.config
 * @Description: ResourceServerConfig
 * @date 2019/11/26 3:05 下午
 **/
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        log.info("ResourceServerConfig------start");
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                //配置需要保护的资源路径
                .antMatchers("/v1/auth/**","/v1/user/**");

    }
}
