package com.caoyuqian.commom.error;

import com.caoyuqian.commom.api.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author qian
 * @version V1.0
 * @Title: GlobalExceptionTranslator
 * @Package: com.caoyuqian.commom.error
 * @Description: 全局异常处理类
 * @date 2019/11/29 3:10 下午
 **/
@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler(ServiceException.class)
    public Result handleError(ServiceException e){
        return Result
                .builder()
                .code(e.getStatus().getCode())
                .msg(e.getMessage())
                .build();
    }
}
