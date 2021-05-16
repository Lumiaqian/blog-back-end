package com.caoyuqian.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: LongArray2StringSerialize
 * @Package: com.caoyuqian.common.json
 * @Description: LongArray2StringSerialize
 * @date 2021/5/16 4:35 下午
 **/
public class LongArray2StringSerialize extends JsonSerializer<List<Long>> {
    @Override
    public void serialize(List<Long> longs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (Long str : longs) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        jsonGenerator.writeString(sb.toString());
    }
}
