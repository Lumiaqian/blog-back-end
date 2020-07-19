package com.caoyuqian.common.error;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import org.springframework.validation.BindException;
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

    /**
     * @param e
     * @return com.caoyuqian.common.api.Result
     * @Description: 处理service异常
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 7:54 下午
     * @since 0.1.0
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleError(ServiceException e) {
        return Result
                .builder()
                .code(e.getStatus().getCode())
                .msg(e.getMessage())
                .build();
    }

    /**
     * @param e
     * @return com.caoyuqian.common.api.Result
     * @Description: 处理validation校验异常
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 9:29 下午
     * @since 0.1.0
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getAllErrors().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(","));
        return Result
                .builder()
                .code(Status.PARAM_ERROR.getCode())
                .msg(errorMsg.toString())
                .build();
    }

}
