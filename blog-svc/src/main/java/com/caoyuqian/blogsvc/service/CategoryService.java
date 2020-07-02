package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreateCateRequest;
import com.caoyuqian.blogapi.vo.CategoryVo;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface CategoryService {

    /**
     * @param request
     * @return java.lang.Integer
     * @Description: 添加新的分类
     * @version 0.1.0
     * @author qian
     * @date 2020/6/30 4:09 下午
     * @since 0.1.0
     */
    CategoryVo add(CreateCateRequest request);

    /**
     * @param requests
     * @return java.util.List<com.caoyuqian.blogapi.vo.CategoryVo>
     * @Description: 批量保存
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 2:07 下午
     * @since 0.1.0
     */
    List<CategoryVo> saveList(List<CreateCateRequest> requests);

    /**
     * @param name
     * @return com.caoyuqian.blogapi.vo.CategoryVo
     * @Description: 根据名称获取
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 4:35 下午
     * @since 0.1.0
     */
    CategoryVo getByName(String name);
}
