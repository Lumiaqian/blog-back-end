package com.caoyuqian.blog.handler;

import com.alibaba.fastjson.JSON;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: UserAuthenticationFailureHandler
 * @Package: com.caoyuqian.blog.handler
 * @Description: 处理用户登录失败
 * @date 2018/8/8 下午10:20
 **/
@Component
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        JsonResult jsonResult=new JsonResult(ResultCode.Login_Failure);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
