package com.caoyuqian.blog.cache;

import com.caoyuqian.blog.utils.FastJsonRedisSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;



/**
 * @author qian
 * @version V1.0
 * @Title: TedisCacheManager
 * @Package: com.caoyuqian.blog.cache
 * @Description: 自定义redisCacheManager
 * @date 2018/8/13 下午3:13
 **/
public class TedisCacheManager extends RedisCacheManager implements ApplicationContextAware, InitializingBean {
    /**
     * Redis 容易出现缓存问题（超时、Redis 宕机等），当使用 spring cache 的注释 Cacheable、Cacheput 等处理缓存问题时，
     * 我们无法使用 try catch 处理出现的异常，所以最后导致结果是整个服务报错无法正常工作。
     * 通过自定义 TedisCacheManager 并继承 RedisCacheManager 来处理异常可以解决这个问题。
     */
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext;
    private Map<String, RedisCacheConfiguration> initialCacheConfiguration = new LinkedHashMap<>();
    //设置CacheManager的值序列化方式为 fastJsonRedisSerializer,但其实RedisCacheConfiguration默认使用StringRedisSerializer序列化key，
    FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public TedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }

    @Override
    public Cache getCache(String name){
        Cache cache=super.getCache(name);
        return  new RedisCacheWrapper(cache);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            add(clazz);
        }
        super.afterPropertiesSet();
    }

    @Override
    protected Collection<RedisCache> loadCaches() {
        List<RedisCache> caches = new LinkedList<>();
        for (Map.Entry<String, RedisCacheConfiguration> entry : initialCacheConfiguration.entrySet()) {
            caches.add(super.createRedisCache(entry.getKey(), entry.getValue()));
        }
        return caches;
    }

    private void add(final Class clazz) {
        ReflectionUtils.doWithMethods(clazz, method -> {
            ReflectionUtils.makeAccessible(method);
            CacheExpire cacheExpire = AnnotationUtils.findAnnotation(method, CacheExpire.class);
            if (cacheExpire == null) {
                return;
            }
            Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
            if (cacheable != null) {
                add(cacheable.cacheNames(), cacheExpire);
                return;
            }
            Caching caching = AnnotationUtils.findAnnotation(method, Caching.class);
            if (caching != null) {
                Cacheable[] cs = caching.cacheable();
                if (cs.length > 0) {
                    for (Cacheable c : cs) {
                        if (cacheExpire != null && c != null) {
                            add(c.cacheNames(), cacheExpire);
                        }
                    }
                }
            } else {
                CacheConfig cacheConfig = AnnotationUtils.findAnnotation(clazz, CacheConfig.class);
                if (cacheConfig != null) {
                    add(cacheConfig.cacheNames(), cacheExpire);
                }
            }
        }, method -> null != AnnotationUtils.findAnnotation(method, CacheExpire.class));
    }

    private void add(String[] cacheNames, CacheExpire cacheExpire) {

        for (String cacheName : cacheNames) {
            if (cacheName == null || "".equals(cacheName.trim())) {
                continue;
            }
            long expire = cacheExpire.expire();
            log.info("cacheName: {}, expire: {}", cacheName, expire);
            if (expire >= 0) {
                // 缓存配置
                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(pair)
                        .entryTtl(Duration.ofSeconds(expire))
                        .disableCachingNullValues();
                initialCacheConfiguration.put(cacheName, config);
            } else {
                log.warn("{} use default expiration.", cacheName);
            }
        }
    }

    protected static class RedisCacheWrapper implements Cache {
        private final Cache cache;
        private final Logger log= LoggerFactory.getLogger(this.getClass());
        RedisCacheWrapper(Cache cache) {
            this.cache = cache;
        }

        @Override
        public String getName() {
            // log.info("name: {}", cache.getName());
            try {
                return cache.getName();
            } catch (Exception e) {
                log.error("getName ---> errmsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public Object getNativeCache() {
            // log.info("nativeCache: {}", cache.getNativeCache());
            try {
                return cache.getNativeCache();
            } catch (Exception e) {
                log.error("getNativeCache ---> errmsg: {}", e.getMessage(), e);
                return null;
            }
        }

        @Override
        public ValueWrapper get(Object o) {
            // log.info("get ---> o: {}", o);
            try {
                return cache.get(o);
            } catch (Exception e) {
                log.error("get ---> o: {}, errmsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public <T> T get(Object o, Class<T> aClass) {
            // log.info("get ---> o: {}, clazz: {}", o, aClass);
            try {
                return cache.get(o, aClass);
            } catch (Exception e) {
                log.error("get ---> o: {}, clazz: {}, errmsg: {}", o, aClass, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public <T> T get(Object o, Callable<T> callable) {
            // log.info("get ---> o: {}", o);
            try {
                return cache.get(o, callable);
            } catch (Exception e) {
                log.error("get ---> o: {}, errmsg: {}", o, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void put(Object o, Object o1) {
            // log.info("put ---> o: {}, o1: {}", o, o1);
            try {
                cache.put(o, o1);
            } catch (Exception e) {
                log.error("put ---> o: {}, o1: {}, errmsg: {}", o, o1, e.getMessage(), e);
            }
        }

        @Override
        public ValueWrapper putIfAbsent(Object o, Object o1) {
            // log.info("putIfAbsent ---> o: {}, o1: {}", o, o1);
            try {
                return cache.putIfAbsent(o, o1);
            } catch (Exception e) {
                log.error("putIfAbsent ---> o: {}, o1: {}, errmsg: {}", o, o1, e.getMessage(), e);
                return null;
            }
        }

        @Override
        public void evict(Object o) {
            // log.info("evict ---> o: {}", o);
            try {
                cache.evict(o);
            } catch (Exception e) {
                log.error("evict ---> o: {}, errmsg: {}", o, e.getMessage(), e);
            }
        }

        @Override
        public void clear() {
            // log.info("clear");
            try {
                cache.clear();
            } catch (Exception e) {
                log.error("clear ---> errmsg: {}", e.getMessage(), e);
            }
        }
    }
}
