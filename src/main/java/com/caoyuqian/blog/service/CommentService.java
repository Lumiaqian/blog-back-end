package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Comment;

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
}
