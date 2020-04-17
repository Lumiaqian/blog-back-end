package com.caoyuqian.common.error;


import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: GlobalExceptionTranslator
 * @Package: com.caoyuqian.commom.error
 * @Description: 自定义无权限的响应处理
 * @date 2019/11/29 3:10 下午
 **/
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.error(accessDeniedException.getMessage());


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JsonUtil.obj2Str(Result.fail(Status.FORBIDDEN, "权限不足无法访问")));
    }

}
