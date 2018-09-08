package com.caoyuqian.blog.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author qian
 * @version V1.0
 * @Title: ToStringSerializer
 * @Package: com.caoyuqian.blog.utils
 * @Description: 转换为String序列化
 * @date 2018/9/8 下午10:50
 **/
public class ToStringSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out=serializer.out;
        if (object==null){
            out.writeNull();
            return;
        }
        String val=object.toString();
        out.writeString(val);
    }
}
