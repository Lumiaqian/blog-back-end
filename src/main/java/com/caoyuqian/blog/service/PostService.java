package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Post;
import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PostService {

    Post getPubPostById(String postId);
    Post getPostById(String postId);
    Post about(String postId);
    Post getPostByTitle(String title);
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
    int updatePost(Post post);
    int updatePostTags(ArrayList list);
    int deletePostTags(Post post);
    int updatePostCates(ArrayList list);
    int deletePostCates(Post post);
}
