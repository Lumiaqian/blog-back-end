package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.aspect.Log;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.impl.CategoryServiceImpl;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryController
 * @Package: com.caoyuqian.blog.controller
 * @Description: 有关category的api
 * @date 2018/9/9 下午6:42
 **/
@RestController
@RequestMapping("lumia/categories")
@Transactional
public class CategoryController {

    private final Logger logger= LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("list")
    @Log("访问网站分类页面")
    public JsonResult list(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize){
        JsonResult jsonResult=new JsonResult();
        // List<Category> categories=categoryService.getCategories();
        PageInfo<Category> pageInfo = categoryService.getCategories(pageNo,pageSize);
        jsonResult.setMessage("获取categories成功！");
        jsonResult.setData(pageInfo);
        return jsonResult;
    }
    @GetMapping("posts/{categoryId}")
    @Log("访问网站详细分类页面")
    public JsonResult getPostsByTag(@PathVariable long categoryId,
                                    @Param("pageNo") int pageNo,
                                    @Param("pageSize") int pageSize){
        JsonResult jsonResult=new JsonResult();
        /*List<Post> posts=postService.getPostsByCate(categoryId);
        posts.sort(Comparator.comparing(Post::getPublicDate).reversed());*/
        PageInfo<Post> pageInfo = postService.getPostsByCate(categoryId,pageNo,pageSize);
        pageInfo.getList().sort(Comparator.comparing(Post::getPublicDate).reversed());
        jsonResult.setMessage("获取成功！");
        jsonResult.setData(pageInfo);
        return jsonResult;
    }

}
