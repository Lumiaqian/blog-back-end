package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreatePostCateRequest;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface PostCategoryService {

    /**
     * @Description: 添加新的文章分类关系
     * @param requests
     * @version 0.1.0
     * @return java.lang.Integer
     * @author qian
     * @date 2020/7/1 2:12 下午
     * @since 0.1.0
     */
    boolean saveList(List<CreatePostCateRequest> requests);
}
