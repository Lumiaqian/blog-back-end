package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.caoyuqian.blog.utils.ToStringSerializer;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Tag
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 标签类
 * @date 2018/8/14 下午2:29
 **/
public class Tag implements Serializable {

    @JSONField(serializeUsing= ToStringSerializer.class)
    private long tagId;//id
    private String tagName;//名称

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
