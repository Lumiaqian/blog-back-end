package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Acate
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018/11/7 下午2:45
 **/
@Getter
@Setter
@Data
public class Acate {
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long cateId;
    private String cateName;
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long fatherId;
    //保存时间即创建时间
    private Date saveDate;
    //修改时间
    private Date editDate;
    //状态，0表示正常，1表示删除，默认为0
    private int status;
    //该分类下文章的数量
    private int count;
}
