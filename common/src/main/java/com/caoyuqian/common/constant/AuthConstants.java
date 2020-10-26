package com.caoyuqian.common.constant;

/**
 * @author lumiaqian
 */
public interface AuthConstants {


    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 别名
     */
   String RSA_ALIAS = "blog";

    /**
     * 密钥库口令
     */
   String RSA_STOREPASS = "lumiaqian";

    /**
     * 密钥口令
     */
   String RSA_KEYPASS = "lumiaqian";

   String RSA_JKS_NAME = "blog.jks";

    String JWT_USER_ID_KEY = "id";

    String JWT_CLIENT_ID_KEY = "client_id";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";
    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN ="/admin/**" ;
}
