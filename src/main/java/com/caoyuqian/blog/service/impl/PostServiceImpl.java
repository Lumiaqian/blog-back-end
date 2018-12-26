package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.cache.CacheExpire;
import com.caoyuqian.blog.mapper.PostMapper;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: PostServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018/8/14 下午3:16
 **/
@Transactional
@Service
public class PostServiceImpl implements PostService{

    private final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostMapper postMapper;

    @Override
    @Cacheable(value = "post",key = "#postId")
    @CacheExpire(60*30)
    public Post getPubPostById(String postId) {
        return postMapper.getPubPostById(postId);
    }

    @Override
    public Post getPostById(String postId) {
        return postMapper.getPostById(postId);
    }

    @Override
    @Cacheable(value = "post",key = "#postId")
    @CacheExpire(60*30)
    public Post about(String postId) {
        return postMapper.about(postId);
    }

    @Override
    public Post getPostByTitle(String title) {
        return postMapper.getPostByTitle(title);
    }

    @Override
    public List<Post> getPost() {
        return postMapper.getPost();
    }

    @Override
    public List<Post> getPostsByTag(long tagId) {
        return postMapper.getPostsByTag(tagId);
    }

    @Override
    public PageInfo<Post> getPostsByTag(long tagId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts = postMapper.getPostsByTag(tagId);
        PageInfo<Post> pageInfo = new PageInfo(posts);
        return pageInfo;
    }

    @Override
    public List<Post> getPostsByCate(long categoryId) {
        return postMapper.getPostsByCate(categoryId);
    }

    @Override
    public PageInfo<Post> getPostsByCate(long categoryId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts = postMapper.getPostsByCate(categoryId);
        PageInfo<Post> postPageInfo = new PageInfo<>(posts);
        return postPageInfo;
    }

    @Override
    public PageInfo<Post> getPostByAdmin(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.getPostByAdmin();
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }


    @Override
    public PageInfo<Post> getPosts(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.getPost();
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }

    @Override
    public PageInfo<Post> getDraftPost(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.getDraftPost();
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }

    @Override
    public PageInfo<Post> getDeletedPost(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.getDeletedPost();
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }

    @Override
    public PageInfo<Post> search(Post post,int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Post> posts=postMapper.search(post);
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return pageInfo;
    }

    @Override
    public int savePost(Post post) {
        logger.info("service: "+post.getSaveDate().toString());
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

    @Override
    public int getCount() {
        return postMapper.getCount();
    }

    @Override
    public int getAllCount() {
        return postMapper.getAllCount();
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int updatePostTags(ArrayList list) {
        return postMapper.updatePostTags(list);
    }

    @Override
    public int updatePostTags(Post oldPost, Post newPost) {
        int flag = postMapper.deletePostTags(oldPost);
        if (flag>0){
            postMapper.savePostTags(newPost);
            return flag;
        }
        return 0;
    }

    @Override
    public int deletePostTags(Post post) {
        return postMapper.deletePostTags(post);
    }

    @Override
    public int updatePostCates(ArrayList list) {
        return postMapper.updatePostCates(list);
    }

    @Override
    public int updatePostCates(Post oldPost, Post newPost) {
        int flag = postMapper.deletePostCates(oldPost);
        if (flag>0){
            postMapper.savePostCategories(newPost);
            return flag;
        }
        return 0;
    }

    @Override
    public int deletePostCates(Post post) {
        return postMapper.deletePostCates(post);
    }

    @Override
    public int deletePostById(String postId) {
        return postMapper.deletePostById(postId);
    }

    @Override
    public int discardPostById(String postId) {
        return postMapper.discardPostById(postId);
    }
}
