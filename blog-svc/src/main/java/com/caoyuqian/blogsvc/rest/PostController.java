package com.caoyuqian.blogsvc.rest;

import com.caoyuqian.blogapi.dto.PostQuery;
import com.caoyuqian.blogsvc.service.PostService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: PostController
 * @Package: com.caoyuqian.blogsvc.rest
 * @Description: post相关api
 * @date 2020/7/1 2:12 下午
 **/
@RestController
@RequestMapping("/v1/blog/post")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("test")
    public Result test(){
        return Result.success("Hello World");
    }

    @GetMapping
    public Result getList(@RequestBody PostQuery query){
        return Result.success(postService.getAllPub(query));
    }

    @GetMapping("/{postId}")
    public Result getPubPostById(@PathVariable Long postId){
        return Result.success(postService.getPubPostById(postId));
    }

    @GetMapping("/{tagId}")
    public Result getPubPostByTagId(@PathVariable("tagId") Long tagId){
        return Result.success(postService.getPubPostByTagId(tagId));
    }

    @GetMapping("/{categoryId}")
    public Result getPubPostByCategoryId(@PathVariable("categoryId") Long categoryId){
        return Result.success(postService.getPubPostByCategoryId(categoryId));
    }
}
