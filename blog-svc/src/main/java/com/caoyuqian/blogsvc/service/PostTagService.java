package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreatePostTagRequest;
import com.caoyuqian.blogapi.vo.PostTagVo;
import com.caoyuqian.blogsvc.entity.PostTag;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface PostTagService {

    /**
     * @param requests
     * @Description: 添加新的文章标签关系
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 2:13 下午
     * @since 0.1.0
     */
    void saveList(List<CreatePostTagRequest> requests);

    /**
     * @param postId
     * @return java.util.List<com.caoyuqian.blogapi.vo.PostTagVo>
     * @Description: 根据postId获取¬
     * @version 0.1.0
     * @author qian
     * @date 2020/7/8 2:54 下午
     * @since 0.1.0
     */
    List<PostTagVo> getByPostId(Long postId);


    /**
     * @param tagId
     * @return java.util.List<com.caoyuqian.blogsvc.entity.PostTag>
     * @Description: 根据tagId获取postTag
     * @version 0.1.0
     * @author qian
     * @date 2020/8/10 9:22 下午
     * @since 0.1.0
     */
    List<PostTag> getByTagId(Long tagId);



}
