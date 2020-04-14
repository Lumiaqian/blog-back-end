package com.caoyuqian.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;

/**
 * @author qian
 * @version V1.0
 * @Title: CustomWebResponseExceptionTranslator
 * @Package: com.caoyuqian.auth.exception
 * @Description: 自定义认证异常翻译类
 * @date 2020/4/13 8:55 下午
 **/
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity translate(Exception e) throws Exception {

        return null;
    }
}
