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
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * 别名
     */
   String RSA_ALIAS = "lumiaqian";

    /**
     * 密钥库口令
     */
   String RSA_STOREPASS = "123456";

    /**
     * 密钥口令
     */
   String RSA_KEYPASS = "123456";

   String RSA_JKS_NAME = "lumiaqian.jks";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";
    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN ="/admin/**" ;

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";

    String USER_ID_KEY = "user_id";

    String USER_NAME_KEY = "username";

    String CLIENT_ID_KEY = "client_id";
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";

    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";
    /**
     * 密码加密方式
     */
    String BCRYPT = "{bcrypt}";

    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "Bearer ";
    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    String LOGOUT_PATH = "/oauth/logout";
}
