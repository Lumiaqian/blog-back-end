package com.caoyuqian.blog.cache;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheExpire {

    @AliasFor("expire")
    long value() default 60L;

    @AliasFor("value")
    long expire() default 60L;
}
