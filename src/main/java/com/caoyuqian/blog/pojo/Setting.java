package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Setting
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018-12-07 16:29
 **/
@Getter
@Setter
@Data
public class Setting implements Serializable {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private long id;
    //头像URL
    private String avatar;
    //格言
    private String motto;
    //个人简介
    private String introduction;
    //博客名称
    private String blogName;
    //开始年份
    private String startYear;
    //结束年份
    private String endYear;
}
