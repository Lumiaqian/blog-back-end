package com.caoyuqian.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: LongSet2StringSerialize
 * @Package: com.caoyuqian.common.json
 * @Description: TOTO
 * @date 2021/5/23 5:50 下午
 **/
public class LongSet2StringSerialize extends JsonSerializer<Set<Long>> {
    @Override
    public void serialize(Set<Long> longs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
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
