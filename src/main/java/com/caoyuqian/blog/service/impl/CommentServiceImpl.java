package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.CommentMapper;
import com.caoyuqian.blog.pojo.Comment;
import com.caoyuqian.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CommentServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: 评论service
 * @date 2019-01-07 14:23
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return commentMapper.getCommentsByPostId(postId);
    }
}
