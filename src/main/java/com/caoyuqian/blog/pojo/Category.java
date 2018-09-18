package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Category
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018/8/14 下午2:31
 **/
public class Category implements Serializable {

    @JSONField(serializeUsing= ToStringSerializer.class)
    private long categoryId;
    private String categoryName;
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long fatherId;
    private int count; //该标签下的文章数目

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", fatherId=" + fatherId +
                ", count=" + count +
                '}';
    }
}
