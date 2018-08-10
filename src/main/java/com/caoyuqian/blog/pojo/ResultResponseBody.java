package com.caoyuqian.blog.pojo;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: ResponseBody
 * @Package: com.caoyuqian.blog.pojo
 * @Description: ResponseBody
 * @date 2018/8/8 下午10:05
 **/
public class ResultResponseBody implements Serializable {
    private String status;
    private String msg;
    private Object result;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
