package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: SavePostRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: SavePostRequest
 * @date 2020/7/3 11:01 上午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePostRequest {

    /**
     * 文章
     */
    private CreatePostRequest post;

    /**
     * 分类
     */
    private List<CreateCateRequest> categories;

    /**
     * 标签
     */
    private List<CreateTagRequest> tags;
}
