package com.caoyuqian.blogsvc.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Post
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 文章实体类
 * @date 2020/4/30 10:31 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("post")
public class Post {

    /**
     * 主键
     */
    @TableId(value = "post_id",type = IdType.ASSIGN_ID)
    private Long postId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章的状态1-->已经发布；0-->草稿；-1-->删除
     */
    private Integer status;

    /**
     * 是否开启评论
     */
    private  boolean isOpenComment;

    /**
     * 发布时间
     */
    private LocalDateTime publicTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
