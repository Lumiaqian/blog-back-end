package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreateCateRequest;

/**
 * @author lumiaqian
 */
public interface CategoryService {

    /**
     * @Description: 添加新的分类
     * @param request
     * @version 0.1.0
     * @return java.lang.Integer
     * @author qian
     * @date 2020/6/30 4:09 下午
     * @since 0.1.0
     */
    Integer add(CreateCateRequest request);
}
