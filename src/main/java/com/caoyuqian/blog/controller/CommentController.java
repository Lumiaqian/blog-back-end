package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.Comment;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.CommentService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CommentController
 * @Package: com.caoyuqian.blog.controller
 * @Description: 评论api
 * @date 2019-01-07 14:20
 **/
@RestController
@RequestMapping("lumia")
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public ResponseEntity<JsonResult> saveComment(@RequestBody Comment comment) {
        JsonResult jsonResult = new JsonResult();
        if (comment == null) {
            jsonResult = new JsonResult(ResultCode.SYS_ERROR);
            return new ResponseEntity<>(jsonResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        SnowFlake snowFlake = new SnowFlake(2, 3);
        comment.setId(snowFlake.nextId());
        comment.setCreateDate(DateUtil.getNow());
        logger.info("需要保存的评论：" + comment.toString());
        commentService.saveComment(comment);
        jsonResult.setMessage("评论成功！");
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @GetMapping("comments")
    public ResponseEntity<JsonResult> getCommentsByPostId(@Param("postId") String postId) {
        JsonResult jsonResult = new JsonResult();
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        if (comments.isEmpty()) {
            jsonResult = new JsonResult(ResultCode.SYS_ERROR);
            return new ResponseEntity<>(jsonResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("获取的评论为：" + comments.toString());
        jsonResult.setMessage("获取文章评论成功！");
        jsonResult.setData(comments);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }
}
