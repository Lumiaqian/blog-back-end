package com.caoyuqian.blogsvc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: PostCategory
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 文章与分类的关系表
 * @date 2020/6/30 3:49 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("post_category")
public class PostCategory {

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
     * 分类id
     */
    private Long categoryId;
}
