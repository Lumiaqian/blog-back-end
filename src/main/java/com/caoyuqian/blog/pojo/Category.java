package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Category
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018/8/14 下午2:31
 **/
@Getter
@Setter
@Data
public class Category implements Serializable {

    @JSONField(serializeUsing= ToStringSerializer.class)
    private long categoryId;
    private String categoryName;
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long fatherId;
    //该标签下的文章数目
    private int count;
}
