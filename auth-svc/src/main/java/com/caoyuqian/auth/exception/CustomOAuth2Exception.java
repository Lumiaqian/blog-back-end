package com.caoyuqian.auth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

public class CustomOAuth2Exception extends OAuth2Exception {

    private int code;

    CustomOAuth2Exception(int code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public int getHttpErrorCode() {
        return this.code;
    }
}
