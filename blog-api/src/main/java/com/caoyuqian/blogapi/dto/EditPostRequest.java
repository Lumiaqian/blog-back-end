package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdatePostRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: EditPostRequest
 * @date 2020/7/3 2:46 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditPostRequest {
    /**
     * 文章
     */
    private UpdatePostRequest post;

    /**
     * 分类
     */
    private List<UpdateCateRequest> categories;

    /**
     * 标签
     */
    private List<UpdateTagRequest> tags;
}
