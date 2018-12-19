package com.caoyuqian.blog.config;

import com.caoyuqian.blog.filter.CorsFilter;
import com.caoyuqian.blog.filter.JwtAuthenticationTokenFilter;
import com.caoyuqian.blog.handler.EntryPointUnauthorizedHandler;
import com.caoyuqian.blog.handler.UserAccessDeniedHandler;
import com.caoyuqian.blog.handler.UserAuthenticationFailureHandler;
import com.caoyuqian.blog.handler.UserAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @author qian
 * @version V1.0
 * @Title: WebSecurityConfig
 * @Package: com.caoyuqian.blog.config
 * @Description: Security配置类
 * @date 2018/8/8 下午9:32
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 未登录
     * */
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    /**
     * 无权限访问
     * */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;
    /**
     * 登录失败
     */
    @Autowired
    private UserAuthenticationFailureHandler userAuthenticationFailureHandler;

    /**
     * 登录成功
     * */
    @Autowired
    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    /**
    *角色验证
    **/
    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    /**
     * token验证拦截器
     **/
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 跨域拦截器
     * */
    @Autowired
    private CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(entryPointUnauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/utils/*").permitAll()
                .antMatchers("/utils/*/*").permitAll()
                .antMatchers("/lumia/*").permitAll()
                .antMatchers("/lumia/*/*").permitAll()
                .antMatchers("/lumia/*/*/*").permitAll()
                //.antMatchers("/posts/qian/*").hasAnyRole("ADMIN")
                //.antMatchers("/").permitAll()
                //.antMatchers("/test").permitAll()
                //.antMatchers("/login").permitAll()
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(userAuthenticationSuccessHandler)
                .failureHandler(userAuthenticationFailureHandler)
                .permitAll()
                .and()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
        ;

    }
}
