package com.caoyuqian.blogsvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caoyuqian.blogapi.dto.CategoryQuery;
import com.caoyuqian.blogapi.dto.CreateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCategoryStatusRequest;
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
    CategoryVo saveOrUpdate(CreateCateRequest request);

    /**
     * @param requests
     * @return java.util.List<com.caoyuqian.blogapi.vo.CategoryVo>
     * @Description: 批量保存
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 2:07 下午
     * @since 0.1.0
     */
    List<CategoryVo> saveOrUpdateList(List<CreateCateRequest> requests);

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

    /**
     * @param requests
     * @return boolean
     * @Description: 批量更新
     * @version 0.1.0
     * @author qian
     * @date 2020/7/7 2:33 下午
     * @since 0.1.0
     */
    boolean updateList(List<UpdateCateRequest> requests);

    /**
     * @param postId
     * @return java.util.List<com.caoyuqian.blogapi.vo.CategoryVo>
     * @Description: 根据postId获取
     * @version 0.1.0
     * @author qian
     * @date 2020/7/8 3:14 下午
     * @since 0.1.0
     */
    List<CategoryVo> getByPostId(Long postId);

    /**
     * @param query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.caoyuqian.blogapi.vo.CategoryVo>
     * @Description: 分页查询tag
     * @version 0.1.0
     * @author qian
     * @date 2020/8/5 8:52 下午
     * @since 0.1.0
     */
    IPage<CategoryVo> getListByPage(CategoryQuery query);

    /**
     * @param request
     * @return void
     * @Description: 根据id逻辑删除
     * @version 0.1.0
     * @author qian
     * @date 2020/8/5 9:12 下午
     * @since 0.1.0
     */
    void updateCategoryStatus(UpdateCategoryStatusRequest request);

    /**
     * @param categoryId
     * @return com.caoyuqian.blogapi.vo.CategoryVo
     * @Description: 根据id获取category
     * @version 0.1.0
     * @author qian
     * @date 2020/8/6 9:27 下午
     * @since 0.1.0
     */
    CategoryVo getCategoryById(Long categoryId);

    /**
     * @param
     * @return java.util.List<com.caoyuqian.blogapi.vo.CategoryVo>
     * @Description: 获取所有已经公布的分类
     * @version 0.1.0
     * @author qian
     * @date 2020/8/10 9:55 下午
     * @since 0.1.0
     */
    List<CategoryVo> getAllPubCategory();
}
