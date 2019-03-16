package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lumiaqian
 */
@Mapper
public interface CommentMapper {

    /**
     * @param comment 评论
     * @return int 保存的结果
     */
    int saveComment(@Param("comment") Comment comment);

    /**
     * @param postId 文章id
     * @return List<Comment>
     * 返回该文章下所有的评论
     */
    List<Comment> getCommentsByPostId(@Param("postId")String postId);
    /**
     * @param fatherId
     * @return List<Comment>
     * 返回该评论下的所有评论
     */
    // List<Comment> getCommentsByFatherId(@Param("fatherId")long fatherId);
     /**
       * @Param: String id
       * @return: Commentator
       * @Author: qian
       * @Description: 根据id获取昵称
       * @Date: 2019-03-16 21:35
      **/
    String getCommentatorById(@Param("id")long id);
}
