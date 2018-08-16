package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Post;

public interface PostService {

    Post getPostById(String postId);
    int savePost(Post post);
    int getCountByTitile(String title);
    int savePostTags(Post post);
    int getCountById(String postId);
    int savePostCategories(Post post);
    int isExistsPostTag(Post post);
    int isExistsPostCate(Post post);
}
