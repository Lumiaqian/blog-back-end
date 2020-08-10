package com.caoyuqian.blogsvc.rest.apm;

import com.caoyuqian.blogapi.dto.*;
import com.caoyuqian.blogsvc.service.PostService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: PostController
 * @Package: com.caoyuqian.blogsvc.rest.apm
 * @Description: 管理平台post api
 * @date 2020/8/4 8:49 下午
 **/
@RestController
@RequestMapping("/v1/blog/admin")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("post")
    public Result save(@Valid @RequestBody SavePostRequest request){
        postService.savePost(request);
        return Result.success();
    }

    @PutMapping("post")
    public Result edit(@Valid @RequestBody EditPostRequest request){
        postService.editPost(request);
        return Result.success();
    }
    @PostMapping("post/status")
    public Result updatePostStatus(@Valid @RequestBody UpdatePostStatusRequest request){
        postService.updateStatus(request);
        return Result.success();
    }

    @PostMapping("post/list")
    public Result getPostList(@Valid @RequestBody PostQuery query){
        return Result.success(postService.getPostList(query));
    }

    @GetMapping("post")
    public Result getManagementPostById(@Param("postId") Long postId,@Param("status") Integer status){
        return Result.success(postService.getManagementPostById(postId,status));
    }

    @PostMapping("post/upload")
    public Result upload(MultipartFile file) throws IOException {
        postService.uploadPost(file);
        return Result.success();
    }
}
