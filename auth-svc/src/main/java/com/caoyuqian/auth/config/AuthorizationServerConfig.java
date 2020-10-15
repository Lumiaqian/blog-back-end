package com.caoyuqian.auth.config;


import com.caoyuqian.auth.exception.CustomWebResponseExceptionTranslator;
import com.caoyuqian.auth.service.impl.UserService;
import com.caoyuqian.common.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.*;

/**
 * @author qian
 * @version V1.0
 * @Title: AuthorizationServerConfig
 * @Package: com.caoyuqian.auth.config
 * @Description: AuthorizationServerConfig
 * @date 2019/11/26 3:00 下午
 **/
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        /**
         * redis 存储有状态方式
         */
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
        /**
         * jwt 无状态方式
         */
        //return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 通过jdbc去查询数据库oauth_client_details表验证clientId信息
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
/*

        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        */
/**
         * jwt 无状态方式
         *//*

        //tokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        // 设置access_token有效时长12小时，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // 设置refresh_token有效时长7天，默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
*/
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        endpoints
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter())
                // 自定义认证异常处理类
                .exceptionTranslator(webResponseExceptionTranslator())
                // refresh token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
                //      1 重复使用：access token过期刷新时， refresh token过期时间未改变，仍以初次生成的时间为准
                //      2 非重复使用：access token过期刷新时， refresh token过期时间延续，在refresh token有效期内刷新便永不失效达到无需再次登录的目的
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * @param
     * @return java.security.KeyPair
     * @Description: 从classpath下的密钥库中获取密钥对(公钥 + 私钥)
     * @version 0.1.0
     * @author qian
     * @date 2020/10/15 2:57 下午
     * @since 0.1.0
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource(AuthConstants.RSA_JKS_NAME), AuthConstants.RSA_STOREPASS.toCharArray());
        return keyStoreKeyFactory.getKeyPair(AuthConstants.RSA_ALIAS, AuthConstants.RSA_STOREPASS.toCharArray());
    }

    /**
     * @param
     * @return org.springframework.security.oauth2.provider.token.TokenEnhancer
     * @Description: Jwt内容增强
     * @version 0.1.0
     * @author qian
     * @date 2020/10/15 3:06 下午
     * @since 0.1.0
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return ((accessToken, authentication) -> {
            Map<String, Object> map = new HashMap<>(16);
            UserDetails user = (UserDetails) authentication.getUserAuthentication().getPrincipal();
            map.put(AuthConstants.JWT_USER_ID_KEY, user.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        });
    }

    /**
     * @param
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     * @Description: 使用非对称加密算法对token签名
     * @version 0.1.0
     * @author qian
     * @date 2020/10/15 3:10 下午
     * @since 0.1.0
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }
}
