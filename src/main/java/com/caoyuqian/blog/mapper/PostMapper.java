package com.caoyuqian.blog.mapper;


import com.caoyuqian.blog.pojo.Post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostMapper {

    Post getPubPostById(@Param("post_id") String postId);

    Post getPostById(@Param("post_id") String postId);

    Post about(@Param("post_id") String postId);

    /* Tag getTagById(@Param("tag_id") long tagId);
     Category getcategoryById(@Param("category_id") long categoryId);*/
    List<Post> getPost();

    List<Post> getPostsByTag(@Param("tag_id") long tagId);

    List<Post> getPostsByCate(@Param("category_id") long categoryId);

    List<Post> getPostByAdmin();

    List<Post> getDraftPost();

    List<Post> getDeletedPost();

    List<Post> search(@Param("post") Post post);

    Post getPostByTitle(String title);

    int savePost(@Param("post") Post post);

    int updatePost(@Param("post") Post post);

    int getCountByTitile(@Param("title") String title);

    int getCountById(@Param("post_id") String postId);

    int savePostTags(@Param("post") Post post);

    int savePostCategories(@Param("post") Post post);

    int isExistsPostTag(@Param("post") Post post);

    int isExistsPostCate(@Param("post") Post post);

    int getCount();

    int getAllCount();

    int updatePostTags(@Param("maps") ArrayList list);

    int updatePostCates(@Param("maps") ArrayList list);

    int deletePostTags(@Param("post") Post post);

    int deletePostCates(@Param("post") Post post);

    int deletePostById(@Param("postId") String postId);

    int discardPostById(@Param("postId") String postId);//将文章扔进垃圾箱

    int updatePostCommentStatus(@Param("map")HashMap<String,Object> map);//更新文章评论状态
}
