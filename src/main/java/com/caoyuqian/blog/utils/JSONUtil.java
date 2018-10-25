package com.caoyuqian.blog.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qian
 * @version V1.0
 * @Title: JSONUtil
 * @Package: com.caoyuqian.blog.utils
 * @Description: JSON字符串工具类
 * @date 2018/8/11 下午11:11
 **/
public class JSONUtil {

     /**
       * @Param: unicode编码的JSON
       * @return:
       * @Author: qian
       * @Description: unicode格式转UTF-8
       * @Date: 2018/8/11 下午11:13
      **/
    public static String unicodeToString(String str){

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }

    public static String objToJson(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> Map<String, Object> jsonToMap(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public static <T> T mapToObj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

//    public static <T> ArrayList<T> listToObjectList(List<T> list){
//        for (T t : list){
//
//        }
//    }
}
