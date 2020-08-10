package com.caoyuqian.blogsvc.rest;

import com.caoyuqian.blogsvc.service.CategoryService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryController
 * @Package: com.caoyuqian.blogsvc.rest
 * @Description: CategoryController
 * @date 2020/8/10 10:07 下午
 **/
@RestController
@RequestMapping("/v1/blog/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result getAllPub(){
        return Result.success(categoryService.getAllPubCategory());
    }
}
