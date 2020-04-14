package com.caoyuqian.auth.exception;

import com.caoyuqian.common.api.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

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
    public ResponseEntity<CustomOAuth2Exception> translate(Exception e) throws Exception {

        log.error(e.getMessage());

        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        // 异常栈获取 CustomOAuth2Exception 异常
        Exception ase = (CustomOAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(CustomOAuth2Exception.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception((CustomOAuth2Exception) ase);
        }
        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new CustomOAuth2Exception(Status.UNAUTHORIZED.getCode(), ase.getMessage()));
        }
        ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase instanceof AccessDeniedException) {
            return handleOAuth2Exception(new CustomOAuth2Exception(Status.FORBIDDEN.getCode(), "权限不足无法访问"));
        }
        ase = (InvalidTokenException) throwableAnalyzer.getFirstThrowableOfType(InvalidTokenException.class, causeChain);
        if (ase instanceof InvalidTokenException) {
            return handleOAuth2Exception(new CustomOAuth2Exception(Status.UNAUTHORIZED.getCode(), "无效的 Access Token"));
        }
        ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class, causeChain);
        if (ase instanceof InvalidGrantException) {
            String msg = ase.getMessage();
            if (msg.contains("Bad credentials")){
                msg = "用户名或密码错误";
            }
            if (msg.contains("User is disabled")) {
                msg = "用户被禁用";
            }
            if (msg.contains("User account has expired")) {
                msg = "用户帐户已过期";
            }
            if (msg.contains("User credentials have expired")) {
                msg = "用户凭据已过期";
            }
            if (msg.contains("User account is locked")) {
                msg = "用户帐户已锁定";
            }
            if (msg.contains("Invalid refresh token")) {
                msg = "无效的 Refresh Token";
            }
            return handleOAuth2Exception(new CustomOAuth2Exception(Status.UNAUTHORIZED.getCode(), msg));
        }
        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase instanceof HttpRequestMethodNotSupportedException) {
            return handleOAuth2Exception(new CustomOAuth2Exception(Status.METHOD_NOT_ALLOWED.getCode(), e.getMessage()));
        }
        return handleOAuth2Exception(new CustomOAuth2Exception(Status.FAILURE.getCode(), e.getMessage()));
    }

    private ResponseEntity<CustomOAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
        int code = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (code == Status.UNAUTHORIZED.getCode() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }
        ResponseEntity<CustomOAuth2Exception> response = new ResponseEntity(e, headers, HttpStatus.valueOf(code));
        return response;
    }

}
