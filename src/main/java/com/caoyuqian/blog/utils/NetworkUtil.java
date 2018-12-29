package com.caoyuqian.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;

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
   public static final HashMap<String,Object> getOsAndBrowserInfo(HttpServletRequest request){
       HashMap<String,Object> map = new HashMap<>();
       String  browserDetails  =   request.getHeader("User-Agent");
       String  userAgent       =   browserDetails;
       String  user            =   userAgent.toLowerCase();
       log.info(userAgent);
       String os = "";
       String browser = "";

       //=================OS Info=======================
       if (userAgent.toLowerCase().indexOf("windows") >= 0 )
       {
           os = "Windows";
       } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
       {
           os = "Mac";
       } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
       {
           os = "Unix";
       } else if(userAgent.toLowerCase().indexOf("android") >= 0)
       {
           os = "Android";
       } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
       {
           os = "IPhone";
       }else{
           os = "UnKnown, More-Info: "+userAgent;
       }
       //===============Browser===========================
       if (user.contains("edge"))
       {
           browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
       } else if (user.contains("msie"))
       {
           String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
           browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
       } else if (user.contains("safari") && user.contains("version"))
       {
           browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                   + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
       } else if ( user.contains("opr") || user.contains("opera"))
       {
           if(user.contains("opera")){
               browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                       +"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
           }else if(user.contains("opr")){
               browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                       .replace("OPR", "Opera");
           }

       } else if (user.contains("chrome"))
       {
           browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
       } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  ||
               (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||
               (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
       {
           browser = "Netscape-?";

       } else if (user.contains("firefox"))
       {
           browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
       } else if(user.contains("rv"))
       {
           String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
           browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);
       } else
       {
           browser = "UnKnown, More-Info: "+userAgent;
       }
       map.put("os",os);
       map.put("browser",browser);
       return map;
   }
}
