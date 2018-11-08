package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Acate
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018/11/7 下午2:45
 **/
public class Acate {
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long cateId;
    private String cateName;
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long fatherId;
    private Date saveDate;//保存时间即创建时间
    private Date editDate;//修改时间
    private int status;//状态，0表示正常，1表示删除，默认为0
    private int count;//该分类下文章的数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getCateId() {
        return cateId;
    }

    public void setCateId(long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Acate{" +
                "cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                ", fatherId=" + fatherId +
                ", saveDate=" + saveDate +
                ", editDate=" + editDate +
                ", status=" + status +
                ", count=" + count +
                '}';
    }
}
