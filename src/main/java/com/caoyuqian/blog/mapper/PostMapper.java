package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.Tag;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface PostMapper {

    Post getPostById(@Param("post_id")String postId);
    Post about(@Param("post_id")String postId);
   /* Tag getTagById(@Param("tag_id") long tagId);
    Category getcategoryById(@Param("category_id") long categoryId);*/
    List<Post> getPost();
    List<Post> getPostsByTag(@Param("tag_id") long tagId);
    int savePost(@Param("post") Post post);
    int getCountByTitile(@Param("title") String title);
    int getCountById(@Param("post_id") String postId);
    int savePostTags(@Param("post") Post post);
    int savePostCategories(@Param("post") Post post);
    int isExistsPostTag(@Param("post")Post post);
    int isExistsPostCate(@Param("post") Post post);
}
