package com.caoyuqian.auth.config;

import com.caoyuqian.auth.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author qian
 * @version V1.0
 * @Title: SecurityConfig
 * @Package: com.caoyuqian.auth.config
 * @Description: SecurityConfig
 * @date 2019/11/26 3:07 下午
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 内存方式用户信息（仅测试）
        /*
        String finalPassword = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(finalPassword).authorities("READ", "WRITE")
                .and()
                .withUser("guest").password(finalPassword).authorities("READ");
                */

        // 数据库方式用户信息
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());

    }
}
