package com.caoyuqian.blog.utils;

import java.util.UUID;

public class StringUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String s = uuid.replaceAll("\\-", "");
        return s;
    }
}
