package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreateTagRequest;

/**
 * @author lumiaqian
 */
public interface TagService {

    /**
     * @Description: 添加新的标签
     * @param request 
     * @version 0.1.0
     * @return java.lang.Integer
     * @author qian
     * @date 2020/6/30 4:11 下午
     * @since 0.1.0
     */
    Integer add(CreateTagRequest request);
}
