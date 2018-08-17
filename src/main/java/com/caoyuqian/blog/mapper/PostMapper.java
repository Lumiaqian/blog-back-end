package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Post;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface PostMapper {

    Post getPostById(@Param("post_id")String postId);
    List<Post> getPost();
    int savePost(@Param("post") Post post);
    int getCountByTitile(@Param("title") String title);
    int getCountById(@Param("post_id") String postId);
    int savePostTags(@Param("post") Post post);
    int savePostCategories(@Param("post") Post post);
    int isExistsPostTag(@Param("post")Post post);
    int isExistsPostCate(@Param("post") Post post);
}
