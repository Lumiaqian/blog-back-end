package com.caoyuqian.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    public static String obj2Str(Object object){
        if (null == object){
            return null;
        }
        try {
            return JSON.toJSONString(object);
        }catch (JSONException e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static String jsonObj2Str(JSONObject jsonObject) {
        if (null == jsonObject){
            return null;
        }
        try {
            return JSON.toJSONString(jsonObject);
        }catch (JSONException e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
