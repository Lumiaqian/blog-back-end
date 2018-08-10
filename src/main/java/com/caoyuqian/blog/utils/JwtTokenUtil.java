package com.caoyuqian.blog.utils;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: JwtTokenUtil
 * @Package: com.caoyuqian.blog.utils
 * @Description: JwtToken工具类，用于生成Token
 * @date 2018/8/8 下午10:30
 **/
public class JwtTokenUtil {
     /**
       * @Param:
       * @return: SecretKey
       * @Author: qian
       * @Description:由字符串生成加密key
       * @Date: 2018/8/9 下午7:01
      **/
    public static SecretKey generalKey(){
        String stringKey="LumiaQian";
        byte[] encodeKey= Base64.getEncoder().encode(stringKey.getBytes());
        SecretKey key=new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
        return key;
    }

     /**
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 生成Token
       * @Date: 2018/8/9 下午7:01
      **/
    public static String CreateToken(String userId,long millis){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;//加密算法
        long nowMillis=System.currentTimeMillis();//获取当前的时间戳
        Date now=new Date(nowMillis);
        SecretKey key=generalKey();
        JwtBuilder builder= Jwts.builder()
                .setId(userId)
                .setIssuedAt(now)
                .setSubject(userId)
                .signWith(signatureAlgorithm,key);
        if (millis>0){
            //设置过期时间
            long expMillis=nowMillis+millis;
            Date exp=new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

     /**
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 解析Token
       * @Date: 2018/8/9 下午7:11
      **/
    public static Claims parseToken(String token){
        SecretKey key=generalKey();
        Claims claims=null;
        try {
            claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return claims;
    }

     /**
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 从token中获得userId
       * @Date: 2018/8/9 下午7:19
      **/
    public static String getUserIdFromToken(String token){
        String userId;
        try {
            final Claims claims=parseToken(token);
            userId=claims.getSubject();
        } catch (Exception e) {
            userId=null;
        }
        return userId;
    }

     /**
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 从token中的过期时间
       * @Date: 2018/8/9 下午7:21
      **/
    public static Date getExpFromToken(String token){
        Date exp;
        try {
            final Claims claims=parseToken(token);
            exp=claims.getExpiration();
        } catch (Exception e) {
            exp=null;
        }
        return exp;
    }

     /**
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 获取当前时间
       * @Date: 2018/8/9 下午7:22
      **/
    private static Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Boolean isTokenExpired(String token){
        final Date exp;
        try {
            exp=getExpFromToken(token);
            Boolean flag=exp.before(generateCurrentDate());
        } catch (Exception e) {
            //过期
            return true;
        }
        return false;
    }
}
