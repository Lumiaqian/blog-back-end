package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.cache.CacheExpire;
import com.caoyuqian.blog.mapper.PostMapper;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Cacheable(value = "post",key = "#postId")
    @CacheExpire(60*30)
    public Post getPostById(String postId) {
        return postMapper.getPostById(postId);
    }

    @Override
    public List<Post> getPost() {
        return postMapper.getPost();
    }

    @Override
    public PageInfo<Post> getPosts(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.getPost();
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
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
