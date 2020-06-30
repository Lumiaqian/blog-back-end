package com.caoyuqian.blogsvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.blogapi.dto.CreatePostRequest;
import com.caoyuqian.blogapi.dto.PostQuery;
import com.caoyuqian.blogapi.vo.PostVo;
import com.caoyuqian.blogsvc.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lumiaqian
 */
public interface PostService {

    /**
     * @param request
     * @return java.lang.Integer
     * @Description: 保存博客
     * @version 0.1.0
     * @author qian
     * @date 2020/6/29 2:26 下午
     * @since 0.1.0
     */
    Integer add(CreatePostRequest request);

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
}
