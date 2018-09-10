package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.ResultResponseBody;
import com.caoyuqian.blog.service.impl.CategoryServiceImpl;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryController {

    private final Logger logger= LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("list")
    public ResultResponseBody list(){
        ResultResponseBody resultResponseBody=new ResultResponseBody();
        List<Category> categories=categoryService.getCategories();
        resultResponseBody.setStatus("200");
        resultResponseBody.setMsg("获取categories成功！");
        resultResponseBody.setResult(categories);
        return resultResponseBody;
    }
    @GetMapping("posts/{categoryId}")
    public ResultResponseBody getPostsByTag(@PathVariable long categoryId){
        ResultResponseBody resultResponseBody=new ResultResponseBody();
        List<Post> posts=postService.getPostsByCate(categoryId);
        posts.sort(Comparator.comparing(Post::getPublicDate).reversed());
        resultResponseBody.setStatus("200");
        resultResponseBody.setMsg("获取成功！");
        resultResponseBody.setResult(posts);
        return resultResponseBody;
    }

}
