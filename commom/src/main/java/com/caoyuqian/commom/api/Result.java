package com.caoyuqian.commom.api;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Result
 * @Package: com.caoyuqian.commom.api
 * @Description: 封装统一返回值
 * @date 2019/11/29 10:25 上午
 **/
@Getter
@Builder
@ToString
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    private Result (Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private Result(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private Result(Status status){
        this.code = status.getCode();
        this.msg = status.getMsg();
    }
    private Result(Status status, T data){
        this(status);
        this.data = data;
    }
    /**
     * 快速创建成功结果并返回结果数据
     * @param data
     * @return Result
     */
    public static Result success(Object data){
        return new Result<>(Status.SUCCESS,data);
    }
    /**
     * 快速创建成功结果并不返回结果数据
     * @return Result
     */
    public static Result success(){
        return new Result<>(Status.SUCCESS);
    }
    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail(Status status){
        return new Result<>(status);
    }
    /**
     * 系统异常类并返回数据
     *
     * @return Result
     */
    public static Result fail(Status status,Object data){
        return new Result<>(status,data);
    }
}
