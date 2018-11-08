package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Atag
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018/11/6 下午2:26
 **/
public class Atag implements Serializable {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private long tagId;//id
    private String tagName;//名称
    private Date saveDate;//保存时间即创建时间
    private Date editDate;//修改时间
    private int status;//状态，0表示正常，1表示删除，默认为0

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
        return "Atag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", saveDate=" + saveDate +
                ", editDate=" + editDate +
                ", status=" + status +
                '}';
    }
}
