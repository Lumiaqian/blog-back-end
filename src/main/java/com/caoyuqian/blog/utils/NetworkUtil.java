package com.caoyuqian.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author qian
 * @version V1.0
 * @Title: NetworkUtil
 * @Package: com.caoyuqian.blog.utils
 * @Description: 获取客户端信息工具
 * @date 2018/9/1 下午4:05
 **/
public class NetworkUtil {

    private final static Logger log= LoggerFactory.getLogger(NetworkUtil.class);

    /**
      * @Param: HttpServletRequest
      * @return: String
      * @Author: qian
      * @Description: 获取客户端ip
      * @Date: 2018/9/1 下午4:16
     **/
   public final static String getIpAddress(HttpServletRequest request){
       String ip = request.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
           if(ip.equals("127.0.0.1")){
               //根据网卡取本机配置的IP
               InetAddress inet=null;
               try {
                   inet = InetAddress.getLocalHost();
               } catch (Exception e) {
                   e.printStackTrace();
               }
               ip= inet.getHostAddress();
           }
       }
       // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
       if(ip != null && ip.length() > 15){
           if(ip.indexOf(",")>0){
               ip = ip.substring(0,ip.indexOf(","));
           }
       }
       return ip;
   }
}
