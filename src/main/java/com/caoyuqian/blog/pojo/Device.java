package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Device
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018-12-29 10:47
 **/
@Getter
@Setter
@Data
public class Device {
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long id;
    private String ip;
    private String city;
    private String deviceManufacturer;
    private String deviceType;
    private String os;
    private String osName;
    private String osVersion;
    private String borderGroup;
    private String borderName;
    private String borderType;
    private String browserManufacturer;
    private String browserVersion;
    private String browserEngine;
    private Date createdTime;
}
