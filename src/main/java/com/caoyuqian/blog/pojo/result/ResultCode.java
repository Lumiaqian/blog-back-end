package com.caoyuqian.blog.pojo.result;

public enum ResultCode {
    /** 成功 */
    SUCCESS("200", "成功！"),

    /** 登录失败 */
    Login_Failure("400", "登录失败！"),

    /** 没有登录！ */
    Not_Login("401", "没有登录！"),

    /** 系统错误 */
    Need_Authorities("403", "权限不足！"),

    /** 文件为空 **/
    File_Empty("400","文件为空！"),

    /** 文件格式错误 **/
    File_Format_ERROR("400","文件格式错误！"),

   /* *//** 参数错误 *//*
    PARAMS_ERROR("403", "参数错误 "),

    *//** 不支持或已经废弃 *//*
    NOT_SUPPORTED("410", "不支持或已经废弃"),

    *//** AuthCode错误 *//*
    INVALID_AUTHCODE("444", "无效的AuthCode"),

    *//** 太频繁的调用 *//*
    TOO_FREQUENT("445", "太频繁的调用"),*/
     /** 未知的错误 */
     UNKONW_ERROR("400","未知错误！"),
    /** 服务器的错误 */
    SYS_ERROR("500", "服务器异常！");

    private ResultCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
