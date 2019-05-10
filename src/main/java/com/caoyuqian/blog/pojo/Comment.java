package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.*;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: comment
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 评论实体类
 * @date 2019-01-04 09:39
 **/
@Getter
@Setter
@Data
public class Comment {
    //评论id
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long id;
    //评论的文章id
    private String postId;
    //评论者
    private String commentator;
    //评论者邮箱
    private String email;
    //评论的内容
    private String content;
    //评论创建时间
    private Date createDate;
    //评论删除时间
    private Date deleteDate;
    //状态
    private boolean status;
    //是否是作者
    private boolean isAuthor;
    //父id
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long fatherId;
    //回复id
    @JSONField(serializeUsing= ToStringSerializer.class)
    private long replyId;
}
