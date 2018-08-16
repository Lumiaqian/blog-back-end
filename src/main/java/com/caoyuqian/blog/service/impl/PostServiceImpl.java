package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.PostMapper;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: PostServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018/8/14 下午3:16
 **/
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostMapper postMapper;

    @Override
    public Post getPostById(String postId) {
        return postMapper.getPostById(postId);
    }

    @Override
    public int savePost(Post post) {
        return postMapper.savePost(post);
    }

    @Override
    public int getCountByTitile(String title) {
        return postMapper.getCountByTitile(title);
    }

    @Override
    public int savePostTags(Post post) {
        return postMapper.savePostTags(post);
    }

    @Override
    public int getCountById(String postId) {
        return postMapper.getCountById(postId);
    }

    @Override
    public int savePostCategories(Post post) {
        return postMapper.savePostCategories(post);
    }

    @Override
    public int isExistsPostTag(Post post) {
        return postMapper.isExistsPostTag(post);
    }

    @Override
    public int isExistsPostCate(Post post) {
        return postMapper.isExistsPostCate(post);
    }
}
