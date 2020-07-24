package com.caoyuqian.common.config;

import com.caoyuqian.common.error.CustomAccessDeniedHandler;
import com.caoyuqian.common.error.CustomAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                /**
                 * redis 存储有状态方式
                 */
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                /**
                 * jwt 无状态方式
                 */
                //.tokenStore(new JwtTokenStore(accessTokenConverter()));
                //.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        log.info("ResourceServerConfig------start");
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                //配置需要保护的资源路径
                .antMatchers("/v1/auth/**");

    }
}
