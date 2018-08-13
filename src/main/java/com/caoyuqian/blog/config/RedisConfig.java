package com.caoyuqian.blog.config;

import com.caoyuqian.blog.cache.TedisCacheManager;
import com.caoyuqian.blog.utils.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author qian
 * @version V1.0
 * @Title: RedisConfig
 * @Package: com.caoyuqian.blog.config
 * @Description: 自定义缓存配置类
 * @date 2018/8/12 下午9:32
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }


    /**
     * 自定义生成key的规则
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig= RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new TedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }
}
