package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: CreatePostCateRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreatePostCateRequest
 * @date 2020/6/30 4:06 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostCateRequest {
    /**
     * 文章id
     */
    private Long postId;

    /**
     * 分类id
     */
    private Long categoryId;
}
