package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Tag
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 标签类
 * @date 2018/8/14 下午2:29
 **/
@Getter
@Setter
@Data
public class Tag implements Serializable {

    //id
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long tagId;
    //名称
    private String tagName;
}
