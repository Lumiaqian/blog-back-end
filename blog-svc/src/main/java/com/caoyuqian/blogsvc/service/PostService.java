package com.caoyuqian.blogsvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caoyuqian.blogapi.dto.*;
import com.caoyuqian.blogapi.dto.PostDto;
import com.caoyuqian.blogapi.vo.ManagementPostVo;
import com.caoyuqian.blogapi.vo.PostVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author lumiaqian
 */
public interface PostService {

    /**
     * @param request
     * @return java.lang.boolean
     * @Description: 添加博客
     * @version 0.1.0
     * @author qian
     * @date 2020/6/29 2:26 下午
     * @since 0.1.0
     */
    PostDto saveOrUpdate(CreatePostRequest request);

    /**
     * @param postQuery
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.caoyuqian.blogapi.vo.PostVo>
     * @Description: 分页获取已经公布的文章
     * @version 0.1.0
     * @author qian
     * @date 2020/6/29 4:10 下午
     * @since 0.1.0
     */
    IPage<PostVo> getAllPub(PostQuery postQuery);

    /**
     * @param file
     * @return void
     * @Description: 上传md格式文章
     * @version 0.1.0
     * @author qian
     * @date 2020/6/30 3:32 下午
     * @since 0.1.0
     */
    void uploadPost(MultipartFile file) throws IOException;


    /**
     * @param request
     * @return void
     * @Description: 保存文章
     * @version 0.1.0
     * @author qian
     * @date 2020/7/3 2:03 下午
     * @since 0.1.0
     */
    void savePost(SavePostRequest request);

    /**
     * @param request
     * @return void
     * @Description: 编辑文章
     * @version 0.1.0
     * @author qian
     * @date 2020/7/3 3:05 下午
     * @since 0.1.0
     */
    void editPost(EditPostRequest request);

    /**
     * @param request
     * @return com.caoyuqian.blogapi.vo.PostVo
     * @Description: 更新文章
     * @version 0.1.0
     * @author qian
     * @date 2020/7/3 3:10 下午
     * @since 0.1.0
     */
    PostDto update(UpdatePostRequest request);

    /**
     * @param postQuery
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.caoyuqian.blogapi.vo.ManagementPostVo>
     * @Description: 管理后台分页获取post列表
     * @version 0.1.0
     * @author qian
     * @date 2020/7/19 2:03 下午
     * @since 0.1.0
     */
    IPage<ManagementPostVo> getPostList(PostQuery postQuery);

    /**
     * @param postId
     * @return com.caoyuqian.blogapi.vo.PostVo
     * @Description: 根据postId获取文章
     * @version 0.1.0
     * @author qian
     * @date 2020/7/19 9:14 下午
     * @since 0.1.0
     */
    PostVo getPubPostById(Long postId);

    /**
     * @param postId
     * @return com.caoyuqian.blogapi.vo.ManagementPostVo
     * @Description: 根据id和status获取ManagementPostVo
     * @version 0.1.0
     * @author qian
     * @date 2020/7/19 9:43 下午
     * @since 0.1.0
     */
    ManagementPostVo getManagementPostById(Long postId, Integer status);

    /**
     * @param status
     * @return void
     * @Description: 更新文章状态
     * @version 0.1.0
     * @author qian
     * @date 2020/7/19 9:59 下午
     * @since 0.1.0
     */
    void updateStatus(UpdatePostStatusRequest status);

    /**
     * @param tagId
     * @return java.util.List<com.caoyuqian.blogapi.vo.PostVo>
     * @Description: 根据tagId获取Post
     * @version 0.1.0
     * @author qian
     * @date 2020/8/10 9:17 下午
     * @since 0.1.0
     */
    List<PostVo> getPubPostByTagId(Long tagId);

    /**
     * @param categoryId
     * @return java.util.List<com.caoyuqian.blogapi.vo.PostVo>
     * @Description: 根据categoryId获取已经公布的文章
     * @version 0.1.0
     * @author qian
     * @date 2020/8/10 9:45 下午
     * @since 0.1.0
     */
    List<PostVo> getPubPostByCategoryId(Long categoryId);
}
