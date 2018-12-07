package com.caoyuqian.blog.handler;

import com.alibaba.fastjson.JSON;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: GlobalExceptionHandler
 * @Package: com.caoyuqian.blog.handler
 * @Description: controller全局异常处理
 * @date 2018/9/21 下午3:12
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void exception(HttpServletRequest request, Exception e, HttpServletResponse response) throws IOException {
        logger.error("{}请求{}出现异常",request.getMethod(),request.getRequestURI(),e);
        JsonResult jsonResult=new JsonResult(ResultCode.SYS_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonResult));
        response.setStatus(500);
    }
}
