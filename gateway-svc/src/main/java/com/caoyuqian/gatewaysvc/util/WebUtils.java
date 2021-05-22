package com.caoyuqian.gatewaysvc.util;

import cn.hutool.json.JSONUtil;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author qian
 * @version V1.0
 * @Title: WebUtils
 * @Package: com.caoyuqian.gatewaysvc.util
 * @Description: TOTO
 * @date 2021/5/22 4:00 下午
 **/
public class WebUtils {
    public static Mono writeFailedToResponse(ServerHttpResponse response, Status resultCode){
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(Result.fail(resultCode));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }
}
