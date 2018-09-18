package com.caoyuqian.blog.pojo.result;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: JsonResult
 * @Package: com.caoyuqian.blog.pojo.result
 * @Description: 响应的JSON格式
 * @date 2018/9/18 下午8:23
 **/
public class JsonResult implements Serializable {
    private String code;
    private String message;
    private Object data;

    public JsonResult() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage("成功！");

    }

    public JsonResult(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public JsonResult(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public JsonResult(ResultCode code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public String getCode() {
        return code;
    }
    public void setCode(ResultCode code) {
        this.code = code.val();
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
