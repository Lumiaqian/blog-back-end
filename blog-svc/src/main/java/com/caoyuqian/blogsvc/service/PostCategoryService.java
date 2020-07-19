package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreatePostCateRequest;
import com.caoyuqian.blogapi.vo.PostCateVo;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface PostCategoryService {

    /**
     * @param requests
     * @return java.lang.Integer
     * @Description: 添加新的文章分类关系
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 2:12 下午
     * @since 0.1.0
     */
    void saveList(List<CreatePostCateRequest> requests);

    /**
     * @param postId
     * @return java.util.List<com.caoyuqian.blogapi.vo.PostCateVo>
     * @Description: 根据postId获得
     * @version 0.1.0
     * @author qian
     * @date 2020/7/8 3:10 下午
     * @since 0.1.0
     */
    List<PostCateVo> getByPostId(Long postId);
}
