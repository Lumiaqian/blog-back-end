package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Post;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostService {

    Post getPostById(String postId);
    Post about(String postId);
    List<Post> getPost();
    List<Post> getPostsByTag(long tagId);
    List<Post> getPostsByCate(long categoryId);
    PageInfo<Post> getPosts(int pageNo,int pageSize);
    int savePost(Post post);
    int getCountByTitile(String title);
    int savePostTags(Post post);
    int getCountById(String postId);
    int savePostCategories(Post post);
    int isExistsPostTag(Post post);
    int isExistsPostCate(Post post);
    int getCount();
}
