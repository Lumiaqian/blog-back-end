package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

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
@Getter
@Setter
@Data
public class Atag implements Serializable {
    @JSONField(serializeUsing = ToStringSerializer.class)
    //id
    private long tagId;
    //名称
    private String tagName;
    //保存时间即创建时间
    private Date saveDate;
    //修改时间
    private Date editDate;
    //状态，0表示正常，1表示删除，默认为0
    private int status;
}
