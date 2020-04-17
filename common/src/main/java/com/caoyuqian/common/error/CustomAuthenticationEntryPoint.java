package com.caoyuqian.common.error;


import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAuthenticationEntryPoint
 * 自定义未授权的响应处理
 * @author lumiaqian
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error(authException.getMessage());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Throwable cause = authException.getCause();
        if (cause instanceof InvalidTokenException) {
            response.getWriter().print(JsonUtil.obj2Str(Result.fail(Status.UNAUTHORIZED, "无效的 Access Token")));
        } else if (cause instanceof InvalidGrantException) {
            response.getWriter().print(JsonUtil.obj2Str(Result.fail(Status.UNAUTHORIZED, "无效的 Refresh Token")));
        } else if (cause instanceof AccessDeniedException) {
            response.getWriter().print(JsonUtil.obj2Str(Result.fail(Status.FORBIDDEN, "权限不足无法访问")));
        } else {
            response.getWriter().print(JsonUtil.obj2Str(Result.fail(Status.UNAUTHORIZED, "尚未认证无法访问")));
        }

        /*
        if (isAjaxRequest(request)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        } else {
            response.sendRedirect("/login");
        }
        */

    }

    /*
    private static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }
    */

}
