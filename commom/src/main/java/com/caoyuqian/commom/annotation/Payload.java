package com.caoyuqian.commom.annotation;

import java.lang.annotation.*;

/**
 * @author lumiaqian
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Payload {
}
