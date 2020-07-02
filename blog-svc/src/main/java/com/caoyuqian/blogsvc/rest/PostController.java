package com.caoyuqian.blogsvc.rest;

import com.caoyuqian.blogsvc.service.PostService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        postService.uploadPost(file);
        return Result.success();
    }
    @GetMapping
    public Result test(){
        return Result.success("Hello World");
    }
}
