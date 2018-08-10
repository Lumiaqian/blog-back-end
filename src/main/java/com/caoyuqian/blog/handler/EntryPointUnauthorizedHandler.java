package com.caoyuqian.blog.handler;

import com.alibaba.fastjson.JSON;
import com.caoyuqian.blog.pojo.ResultResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: EntryPointUnauthorizedHandler
 * @Package: com.caoyuqian.blog.handler
 * @Description: 未登录状态处理
 * @date 2018/8/8 下午10:09
 **/
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultResponseBody responseBody=new ResultResponseBody();
        responseBody.setStatus("401");
        responseBody.setMsg("Need Authorities!");
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
