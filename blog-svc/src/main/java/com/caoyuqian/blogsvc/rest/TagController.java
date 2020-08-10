package com.caoyuqian.blogsvc.rest;

import com.caoyuqian.blogsvc.service.TagService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @version V1.0
 * @Title: TagController
 * @Package: com.caoyuqian.blogsvc.rest
 * @Description: TagController
 * @date 2020/8/10 9:05 下午
 **/
@RestController
@RequestMapping("/v1/blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public Result getList(){
        return Result.success(tagService.getAllTags());
    }

}
