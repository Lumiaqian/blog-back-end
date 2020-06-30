package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: CreatePostTagRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreatePostTagRequest
 * @date 2020/6/30 4:05 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostTagRequest {
    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标签id
     */
    private Long tagId;
}
