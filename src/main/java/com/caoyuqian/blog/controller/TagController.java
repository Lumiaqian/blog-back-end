package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import com.caoyuqian.blog.service.impl.TagServiceImpl;
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
 * @Title: TagController
 * @Package: com.caoyuqian.blog.controller
 * @Description: TOTO
 * @date 2018/9/7 下午8:36
 **/
@RestController
@RequestMapping("lumia/tags")
@Transactional
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(TagController.class);
    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("list")
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult();
        List<Tag> tags;
        tags = tagService.getTags();
        jsonResult.setMessage("获取tags成功！");
        jsonResult.setData(tags);
        return jsonResult;
    }

    @GetMapping("posts/{tagId}")
    public JsonResult getPostsByTag(@PathVariable long tagId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
       /* List<Post> posts;
        posts = postService.getPostsByTag(tagId);
        posts.sort(Comparator.comparing(Post::getPublicDate).reversed());
        */
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        PageInfo<Post> pageInfo = postService.getPostsByTag(tagId,pageNo,pageSize);
        pageInfo.getList().sort(Comparator.comparing(Post::getPublicDate).reversed());
        jsonResult.setMessage("获取成功！");
        jsonResult.setData(pageInfo);
        return jsonResult;
    }
}
