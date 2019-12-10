package com.caoyuqian.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
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
            final String str = JSON.toJSONString(object);
            return str;
        }catch (JSONException e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
