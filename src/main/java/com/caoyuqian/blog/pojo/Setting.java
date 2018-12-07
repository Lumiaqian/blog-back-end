package com.caoyuqian.blog.pojo;

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
    private long id;
    private String avatar;//头像URL
    private String motto;//格言
    private String introduction;//个人简介

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", motto='" + motto + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
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
