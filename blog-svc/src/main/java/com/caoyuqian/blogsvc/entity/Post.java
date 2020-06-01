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
 * @Title: Post
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 文章实体类
 * @date 2020/4/30 10:31 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("post")
public class Post {

    @TableId(value = "post_id",type = IdType.ASSIGN_ID)
    private Integer postId;
}
