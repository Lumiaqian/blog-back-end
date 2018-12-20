package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.CategoryService;
import com.caoyuqian.blog.service.PostService;
import com.caoyuqian.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: CountController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: TOTO
 * @date 2018/9/28 下午8:17
 **/
@RestController
@Transactional
@RequestMapping("lumia")
public class LcountController {
    private final Logger logger= LoggerFactory.getLogger(LcountController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("count")
    public JsonResult count(){
        JsonResult jsonResult=new JsonResult();
        int postCount=postService.getCount();
        int tagCount=tagService.getCount();
        int cateCount=categoryService.getCount();
        Map<String,Integer> map=new HashMap<>(16);
        map.put("postCount",postCount);
        map.put("tagCount",tagCount);
        map.put("cateCount",cateCount);
        jsonResult.setData(map);
        return jsonResult;
    }
}
