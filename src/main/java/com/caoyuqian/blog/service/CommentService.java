package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Comment;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CommentService
 * @Package: com.caoyuqian.blog.service
 * @Description: TOTO
 * @date 2019-01-07 14:22
 **/
public interface CommentService {

    int saveComment(Comment comment);

    List<Comment> getCommentsByPostId(String postId);

    String getCommentatorById(long id);

    int getCount();

    int getCountByPost(String postId);
}
