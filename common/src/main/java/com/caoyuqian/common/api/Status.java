package com.caoyuqian.common.api;

import lombok.AllArgsConstructor;

/**
 * @author lumiaqian
 */
@AllArgsConstructor
public enum Status {

    //处理成功
    SUCCESS(200, "SUCCESS"),

    //处理失败
    FAILURE(500, "FAILURE"),

    //未认证
    UNAUTHORIZED(401, "UNAUTHORIZED"),

    //未激活
    NOT_ACTIVE(402, "Account Not Active"),

    //访问拒绝
    FORBIDDEN(403, "FORBIDDEN"),

    /**
     * Method Not Allowed
     * 方法不允许
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    //数据库错误
    DB_ERROR(503, "Error Querying Database"),

    //参数错误
    PARAM_ERROR(501, "Parameter Error"),

    //参数为空
    PARAM_IS_NULL(502, "Parameter Is Null"),

    FORBIDDEN_OPERATION(302, "演示环境禁止修改、删除重要数据，请本地部署后测试");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsg(String msg) {
        return String.format(this.msg, null == msg ? "" : msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
