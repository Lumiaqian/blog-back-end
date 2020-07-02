package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreatePostTagRequest;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface PostTagService {

    /**
     * @Description: 添加新的文章标签关系
     * @param requests
     * @version 0.1.0
     * @return java.lang.boolean
     * @author qian
     * @date 2020/7/1 2:13 下午
     * @since 0.1.0
     */
    boolean saveList(List<CreatePostTagRequest> requests);
}
