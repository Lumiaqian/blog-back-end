package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Setting
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018-12-07 16:29
 **/
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

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", motto='" + motto + '\'' +
                ", introduction='" + introduction + '\'' +
                ", blogName='" + blogName + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                '}';
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
