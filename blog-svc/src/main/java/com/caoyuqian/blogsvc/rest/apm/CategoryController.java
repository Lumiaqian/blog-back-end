package com.caoyuqian.blogsvc.rest.apm;

import com.caoyuqian.blogapi.dto.CategoryQuery;
import com.caoyuqian.blogapi.dto.CreateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCategoryStatusRequest;
import com.caoyuqian.blogsvc.service.CategoryService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryController
 * @Package: com.caoyuqian.blogsvc.rest.apm
 * @Description: 管理平台category api
 * @date 2020/8/5 8:43 下午
 **/
@RestController
@RequestMapping("/v1/blog/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("category/list")
    public Result getListByPage(@RequestBody CategoryQuery query){
        return Result.success(categoryService.getListByPage(query));
    }

    @PostMapping("category")
    public Result addCategory(@Valid @RequestBody CreateCateRequest request){
        return Result.success(categoryService.saveOrUpdate(request));
    }

    @PutMapping("category")
    public Result updateCategories(@Valid @RequestBody List<UpdateCateRequest> requests){
        return Result.success(categoryService.updateList(requests));
    }

    @PostMapping("category/status")
    public Result updateCategoryStatus(@Valid @RequestBody UpdateCategoryStatusRequest request){
        categoryService.updateCategoryStatus(request);
        return Result.success();
    }

    @GetMapping("category/{categoryId}")
    public Result getCategoryById(@PathVariable Long categoryId){
        return Result.success(categoryService.getCategoryById(categoryId));
    }
}
