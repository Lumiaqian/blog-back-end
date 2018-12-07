package com.caoyuqian.blog.handler;

import com.alibaba.fastjson.JSON;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: UserAccessDeniedHandler
 * @Package: com.caoyuqian.blog.handler
 * @Description: 无权访问
 * @date 2018/8/9 下午8:06
 **/
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JsonResult jsonResult=new JsonResult(ResultCode.Need_Authorities);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
        response.setStatus(403);
    }
}
