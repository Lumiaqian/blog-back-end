package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.ResultResponseBody;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import com.caoyuqian.blog.service.impl.TagServiceImpl;
import com.caoyuqian.blog.utils.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: TagController
 * @Package: com.caoyuqian.blog.controller
 * @Description: TOTO
 * @date 2018/9/7 下午8:36
 **/
@RestController
@RequestMapping("lumia/tags")
public class TagController {
    private final Logger logger= LoggerFactory.getLogger(TagController.class);
    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("list")
    public ResultResponseBody list(){
        ResultResponseBody resultResponseBody=new ResultResponseBody();
        List<Tag> tags;
        tags=tagService.getTags();
        resultResponseBody.setStatus("200");
        resultResponseBody.setMsg("获取tags成功！");
        resultResponseBody.setResult(tags);
        return resultResponseBody;
    }
    @GetMapping("posts/{tagId}")
    public ResultResponseBody getPostsByTag(@PathVariable long tagId){
        ResultResponseBody resultResponseBody=new ResultResponseBody();
        List<Post> posts;
        posts=postService.getPostsByTag(tagId);
        Sort.quickSort(posts);
        resultResponseBody.setStatus("200");
        resultResponseBody.setMsg("获取成功！");
        resultResponseBody.setResult(posts);
        return resultResponseBody;
    }
}
