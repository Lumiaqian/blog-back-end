package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreatePostTagRequest;

/**
 * @author lumiaqian
 */
public interface PostTagService {

    /**
     * @Description: 添加新的文章标签关系
     * @param request 
     * @version 0.1.0
     * @return java.lang.Integer
     * @author qian
     * @date 2020/6/30 4:10 下午
     * @since 0.1.0
     */
    Integer add(CreatePostTagRequest request);
}
