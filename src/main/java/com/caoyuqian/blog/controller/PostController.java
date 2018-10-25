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
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

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
       * @Param:
       * @return:
       * @Author: qian
       * @Description: 解析MarkDown
       * @Date: 2018/8/16 下午8:24
      **/
    @PutMapping("upload")
    public JsonResult upload(MultipartFile file) throws Exception {
        JsonResult jsonResult=new JsonResult();
        if (Objects.isNull(file) || file.isEmpty()) {
            //上传文件为空
            logger.error("文件为空！");
            jsonResult.setCode(ResultCode.File_Empty);
            return jsonResult;
        }
        BufferedReader br=new BufferedReader(new InputStreamReader(file.getInputStream()));
        String context=FileCopyUtils.copyToString(br);
        //获取markdown中的内容，转换成json格式
        JSONObject object=parseArticle(file.getOriginalFilename(),context);
        Post post=new Post();
        //将获取的JSOn格式转换为post对象
        post= JSONUtil.jsonToObj(object.toJSONString(), Post.class);
        //文件保存路径
        Path path= Paths.get(UPLOAD_FOLDER+"/"+ file.getOriginalFilename());
        post.setPath(path.toString());
        //更新数据库中的内容
        int code=0;
        //更新category
        long fatherId = -1;
        if (post.getCategories()!=null){
            logger.info(post.getCategories().toString());
            List<Category> categories=new ArrayList<>();
            for(Category category:post.getCategories()){
                code=categoryService.getCountByName(category.getCategoryName());
                if (code==0){
                    category.setFatherId(fatherId);
                    code=categoryService.saveCategory(category);
                    if (code==0)
                        logger.error("category存入数据库失败！");
                }else {
                    logger.info("category已经存在！");
                    category=categoryService.getCategoryByName(category.getCategoryName());
                    fatherId=category.getCategoryId();
                    category.setCount(0);
                }
                logger.info(String.valueOf(fatherId));
                categories.add(category);
            }
            post.setCategories(categories);
        }
        code=0;//重置code
        //更新tag
        if (post.getTags()!=null){
            logger.info(post.getTags().toString());
            List<Tag> tags=new ArrayList<>();
            for (Tag tag:post.getTags()){
                code=tagService.getCountByName(tag.getTagName());
                if (code==0){
                    code=tagService.saveTag(tag);
                    if (code==0)
                        logger.error("tag存入数据库失败！");
                }else {
                    tag=tagService.getTagByName(tag.getTagName());
                }
                tags.add(tag);
            }
            post.setTags(tags);
        }

        //更新post
        code=0;
        code=postService.getCountById(post.getPostId());
        if (code==0){
            post.setStatus(1);
            code=postService.savePost(post);
            if (code==0)
                logger.error("post存入数据库失败！");
        }
        //更新post_tag,post_category
        code=0;
        code=postService.isExistsPostTag(post);
        if (code==0){
            //post_tag关系不存在
            code=postService.savePostTags(post);
            if (code==0)
                logger.error("post_tag保存失败！");
        }
        code=0;
        code=postService.isExistsPostCate(post);
        if (code==0){
            //post_cate关系不存在
            code=postService.savePostCategories(post);
            if (code==0)
                logger.error("post_category保存失败！");
        }
        logger.info("解析出的post为："+post.toString());
        //保存到elasticsearch中
        //postRepositoryService.save(post);
        //保存到服务器中

        //获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (suffixName.equals("md")){
            //文件格式错误
            logger.error("文件格式错误！");
            jsonResult.setCode(ResultCode.File_Format_ERROR);
            return jsonResult;
        }
        try {
            byte[] bytes = file.getBytes();

            //如果没有post/文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER+"/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            jsonResult.setMessage("解析成功！");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("服务器异常");
        }
        return jsonResult;
    }

     /**
       * @Param: pageNo PageSize
       * @return:
       * @Author: qian
       * @Description: 获取post列表
       * @Date: 2018/8/16 下午8:25
      **/
    @GetMapping("list")
    public JsonResult getPots(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize){
        JsonResult jsonResult=new JsonResult();
        /*List<Post> posts=new ArrayList<>();
        posts=postService.getPost();
        logger.info(String.valueOf(posts.size()));*/
        logger.info("pageNo: "+pageNo+" pageSize: "+pageSize);
        try {
            PageInfo<Post> posts=postService.getPosts(pageNo,pageSize);
            // posts.getList().sort(Comparator.comparing(Post::getPublicDate).reversed());
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
    public JsonResult getPost(@PathVariable String postId){
        JsonResult jsonResult=new JsonResult();
        Post post=postService.getPubPostById(postId);
        int count=1;//默认阅读次数为一次
        if (redisManger.get(postId)!=null){
            count=(Integer) redisManger.get(postId);
            post.setWatchCount(count);
        }
        redisManger.set(postId,count+1);
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
    public JsonResult about(){
        JsonResult jsonResult=new JsonResult();
        String postId="20180315185058";
        Post post=postService.about(postId);
        jsonResult.setMessage("获取about成功！");
        jsonResult.setData(post);
        return jsonResult;
    }
    @PostMapping("public")
    public JsonResult publicPost(){
        JsonResult jsonResult=new JsonResult();

        return jsonResult;
    }

    @GetMapping("sync")
    public JsonResult sync(){
        JsonResult jsonResult=new JsonResult();
        List<Post> posts=postService.getPost();
        logger.info("PostSize: "+posts.size());
        postRepositoryService.saveAll(posts);
        jsonResult.setMessage("同步成功！");
        jsonResult.setData(posts);
        return jsonResult;
    }

    @PostMapping("search")
    public JsonResult search(@RequestBody Map map){
        JsonResult jsonResult=new JsonResult();
        Integer pageNum= (Integer) map.get("pageNum");
        Integer pageSize=(Integer) map.get("pageSize");
        logger.info("pageNum: "+pageNum+"pageSize: "+pageSize);
        String key=(String) map.get("keyWorld");
        Page<Post> posts=postRepositoryService.getListByKey(pageNum,pageSize,key);
        jsonResult.setData(posts);
        return jsonResult;
    }

    private JSONObject parseArticle(String fileName,String context) throws ParseException {
        JSONObject rst=new JSONObject();
        context=StringUtils.trim(context);
        Category category=new Category();
        Tag tag=new Tag();
        String frontMatter= StringUtils.substringBefore(context,"---");
        if (StringUtils.isBlank(frontMatter)){
            context=StringUtils.substringAfter(context,"---");
            frontMatter= StringUtils.substringBefore(context,"---");
        }
        Yaml yaml=new Yaml();
        Map elemts;
        try {
            elemts=yaml.load(frontMatter);
        } catch (Exception e) {
            Date date=new Date(System.currentTimeMillis());
            rst.put("title",StringUtils.substringBeforeLast(fileName, "."));
            rst.put("content",context);
            rst.put("postId", DateUtil.DateToString(date));
            rst.put("publicDate",date);
            rst.put("editDate",date);
            rst.put("saveDate",date);
            category.setCategoryName("默认分类");
            rst.put("category",category);

            return rst;
        }
        //获取标题
        String title= (String) elemts.get("title");
        if (StringUtils.isBlank(title)){
            title=StringUtils.substringBeforeLast(fileName, ".");
        }
        //获取内容
        //String content=context;
        String content=StringUtils.substringAfter(context,frontMatter);
        if (StringUtils.startsWith(content, "---")) {
            content = StringUtils.substringAfter(content, "---");
        }
        //获取时间

        Date pubilcDate= (Date) elemts.get("date");

        //获取tags
        List<Tag> tags=new ArrayList<>();
        SnowFlake tagSnow=new SnowFlake(2,3);
        Object arr=elemts.get("tags");
        if (arr==null){
            tags=null;
        } else if (arr instanceof String){
            tag.setTagId(tagSnow.nextId());
            tag.setTagName(arr.toString());
            tags.add(tag);
        } else {
            for ( String tag1 : (List<String>) arr) {
                if (StringUtils.isBlank(tag1)) {

                } else {
                    tag.setTagId(tagSnow.nextId());
                    tag.setTagName(tag1);
                    tags.add(tag);
                }
            }
        }
        //获取分类
        List<Category> categories=new ArrayList<>();
        List<String> list=new ArrayList<>();
        SnowFlake snowFlake=new SnowFlake(2,3);
        logger.info("初始化的list: "+list.isEmpty());
        Object cate=elemts.get("categories");
        if (cate==null){
            categories=null;
        }else if (cate instanceof String){
            logger.info("-----进入判断是否只有一个标签------");
             category.setCategoryName(cate.toString());
             category.setCategoryId(snowFlake.nextId());
             categories.add(category);
        }else {
            list = (List<String>) elemts.get("categories");
        }
        if (!list.isEmpty()){
         for ( int i=0;i<list.size();i++) {
             logger.info(list.get(i));
             category=new Category();
             category.setCategoryName(list.get(i));
             category.setCategoryId(snowFlake.nextId());
             if (i==0){
                 //顶层目录
                 category.setFatherId(-1);
             }else {
                 category.setFatherId(categories.get(i-1).getCategoryId());
             }
             categories.add(category);
         }
        }

        rst.put("title",title);
        rst.put("postId",DateUtil.DateToString(pubilcDate));
        rst.put("content",content);
        rst.put("publicDate",pubilcDate);
        rst.put("editDate",pubilcDate);
        rst.put("saveDate",pubilcDate);
        rst.put("categories",categories);
        if (tags==null){
            rst.put("tags",tags);
        } else if (!tags.isEmpty()){
            rst.put("tags",tags);
        }
        return rst;
    }
}
