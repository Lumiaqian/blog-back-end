package com.caoyuqian.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
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
        StringBuilder sb = new StringBuilder();
        for (Long str : longs) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);
        }
        jsonGenerator.writeObject(Arrays.asList(StringUtils.split(sb.toString(),",")));
    }
}
