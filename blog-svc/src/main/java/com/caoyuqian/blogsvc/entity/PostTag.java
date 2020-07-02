package com.caoyuqian.blogsvc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: PostTag
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 文章与标签的关系表
 * @date 2020/6/30 3:53 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("post_tag")
public class PostTag {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标签id
     */
    private Long tagId;
}
