package com.caoyuqian.gatewaysvc.error;


import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * CustomAuthenticationEntryPoint
 * 自定义未授权的响应处理
 * @author lumiaqian
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        log.error(e.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JsonUtil.obj2Str(Result.fail(Status.UNAUTHORIZED,"token无效或已过期"));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
