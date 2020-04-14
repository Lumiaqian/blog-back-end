package com.caoyuqian.auth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: CustomOAuth2ExceptionSerializer
 * @Package: com.caoyuqian.auth.exception
 * @Description: 自定义认证异常序列化类
 * @date 2020/4/13 8:57 下午
 **/
public class CustomOAuth2ExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {
    public CustomOAuth2ExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        gen.writeStartObject();
        gen.writeNumberField("code", value.getHttpErrorCode());
        gen.writeStringField("msg", value.getMessage());
        gen.writeNumberField("timestamp", LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());

        if (value.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }

        gen.writeEndObject();
    }
}
