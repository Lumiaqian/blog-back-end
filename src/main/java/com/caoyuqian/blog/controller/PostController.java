package com.caoyuqian.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.impl.CategoryServiceImpl;
import com.caoyuqian.blog.service.impl.PostRepositoryServiceImpl;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import com.caoyuqian.blog.service.impl.TagServiceImpl;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.RedisManger;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

/**
 * @author qian
 * @version V1.0
 * @Title: PostController
 * @Package: com.caoyuqian.blog.controller
 * @Description: 管理后台的post相关的Url
 * @date 2018/8/14 下午4:33
 **/
@RequestMapping("lumia/posts")
@RestController
@Transactional
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private RedisManger redisManger;
    @Autowired
    private PostRepositoryServiceImpl postRepositoryService;

    /**
     * @Param: pageNo PageSize
     * @return:
     * @Author: qian
     * @Description: 获取post列表
     * @Date: 2018/8/16 下午8:25
     **/
    @GetMapping("list")
    public JsonResult getPosts(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        try {
            PageInfo<Post> posts = postService.getPosts(pageNo, pageSize);
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(posts);
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * @Param: postId
     * @return:
     * @Author: qian
     * @Description: 根据postId获取文章
     * @Date: 2018/8/16 下午8:43
     **/
    @GetMapping("{postId}")
    public JsonResult getPost(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        Post post = postService.getPubPostById(postId);
        //默认阅读次数
        int count = 0;
        String key ="WatchCount:"+postId;
        if (redisManger.get(key) != null) {
            count = (Integer) redisManger.get(key);
            post.setWatchCount(count);
        }else {
            post.setWatchCount(1);
        }
        redisManger.set(key, count + 1);
        jsonResult.setMessage("获取post成功！");
        jsonResult.setData(post);
        return jsonResult;
    }

    /**
     * @Param:
     * @return:
     * @Author: qian
     * @Description: 获取关于我文章的API
     * @Date: 2018/9/7 下午8:56
     **/
    @GetMapping("about")
    public JsonResult about() {
        JsonResult jsonResult = new JsonResult();
        String postId = "20180315185058";
        Post post = postService.about(postId);
        jsonResult.setMessage("获取about成功！");
        jsonResult.setData(post);
        return jsonResult;
    }

    @PostMapping("public")
    public JsonResult publicPost() {
        JsonResult jsonResult = new JsonResult();

        return jsonResult;
    }

    @GetMapping("sync")
    public JsonResult sync() {
        JsonResult jsonResult = new JsonResult();
        List<Post> posts = postService.getPost();
        logger.info("PostSize: " + posts.size());
        postRepositoryService.saveAll(posts);
        jsonResult.setMessage("同步成功！");
        jsonResult.setData(posts);
        return jsonResult;
    }

    @PostMapping("search")
    public JsonResult search(@RequestBody Map map) {
        JsonResult jsonResult = new JsonResult();
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        logger.info("pageNum: " + pageNum + " pageSize: " + pageSize);
        String key = (String) map.get("keyWord");
        Page<Post> posts = postRepositoryService.getListByKey(pageNum, pageSize, key);
        List<Post> postList = posts.getContent();
        // logger.info(postList.toString());
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("posts",postList);
        hashMap.put("total",posts.getTotalElements());
        jsonResult.setData(hashMap);
        return jsonResult;
    }

}
