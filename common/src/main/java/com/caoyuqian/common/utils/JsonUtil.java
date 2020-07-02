package com.caoyuqian.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: JsonUtil
 * @Package: com.caoyuqian.common.utils
 * @Description: JsonUtil
 * @date 2019/12/10 5:46 下午
 **/
@Slf4j
@Component
public class JsonUtil {

    /**
     * @param object
     * @return java.lang.String
     * @Description: json对象转json字符串
     * @version 0.1.0
     * @author qian
     * @date 2020/6/30 4:33 下午
     * @since 0.1.0
     */
    public static String obj2Str(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return JSON.toJSONString(object);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param jsonObject
     * @return java.lang.String
     * @Description: json对象转json字符串
     * @version 0.1.0
     * @author qian
     * @date 2020/6/30 4:33 下午
     * @since 0.1.0
     */
    public static String jsonObj2Str(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        try {
            return JSON.toJSONString(jsonObject);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param jsonStr
     * @param objType
     * @return T
     * @Description: json字符串转json对象
     * @version 0.1.0
     * @author qian
     * @date 2020/6/30 4:34 下午
     * @since 0.1.0
     */
    public static <T> T jsonStr2Obj(String jsonStr, Class<T> objType) {
        if (StringUtils.isEmpty(jsonStr) || null == objType) {
            return null;
        }
        try {
            return JSON.parseObject(jsonStr, objType);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param map
     * @param clazz
     * @return T
     * @Description: map to obj
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 7:42 下午
     * @since 0.1.0
     */
    public static <T> T map2Obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        if (map == null || map.isEmpty()){
            return null;
        }
        try {
            return JSON.parseObject(JSON.toJSONString(map), clazz);
        }catch (JSONException e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }
}
