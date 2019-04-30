package com.caoyuqian.blog.handler;

import com.alibaba.fastjson.JSON;
import com.caoyuqian.blog.aspect.Log;
import com.caoyuqian.blog.pojo.AuthUser;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: UserAuthenticationSuccessHandler
 * @Package: com.caoyuqian.blog.handler
 * @Description: 处理用户登录成功
 * @date 2018/8/8 下午10:24
 **/
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Log("用户登录！")
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JsonResult jsonResult=new JsonResult();
        AuthUser user=(AuthUser) authentication.getPrincipal();
        logger.info("authUserName: "+user.getUsername());
        String token= JwtTokenUtil.CreateToken(user.getUsername(),1000*60*60*24);
        jsonResult.setMessage("登录成功！");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Authorization","Bearer "+token);//将Token放入响应头response header中
        response.getWriter().write(JSON.toJSONString(jsonResult));
    }
}
