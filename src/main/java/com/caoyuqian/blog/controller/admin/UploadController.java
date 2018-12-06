package com.caoyuqian.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.*;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.CategoryService;
import com.caoyuqian.blog.service.PostService;
import com.caoyuqian.blog.service.TagService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qian
 * @version V1.0
 * @Title: UploadController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: TOTO
 * @date 2018-12-06 10:54
 **/
@RestController
@Transactional
public class UploadController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    private final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("upload")
    public JsonResult upload(MultipartFile file) throws Exception {
        JsonResult jsonResult = new JsonResult();
        if (Objects.isNull(file) || file.isEmpty()) {
            //上传文件为空
            logger.error("文件为空！");
            jsonResult.setCode(ResultCode.File_Empty);
            return jsonResult;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String context = FileCopyUtils.copyToString(br);
        //获取markdown中的内容，转换成json格式
        JSONObject object = parseArticle(file.getOriginalFilename(), context);
        Post post = new Post();
        //将获取的JSOn格式转换为post对象
        post = JSONUtil.jsonToObj(object.toJSONString(), Post.class);
        // logger.info(post.getCategories().toString());
        //文件保存路径
        Path path = Paths.get(UPLOAD_FOLDER + "/" + file.getOriginalFilename());
        post.setPath(path.toString());
        //保存到服务器中
        //获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (suffixName.equals("md")) {
            //文件格式错误
            logger.error("文件格式错误！");
            jsonResult.setCode(ResultCode.File_Format_ERROR);
            return jsonResult;
        }
        try {
            byte[] bytes = file.getBytes();
            //如果没有post/文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER + "/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            jsonResult.setMessage("解析成功！");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("服务器异常");
        }
        //更新数据库中的内容
        int code = 0;
        //更新category
        AtomicLong fatherId = new AtomicLong();
        fatherId.set(-1);
        if (post.getCategories() != null) {
            logger.info(post.getCategories().toString());
            List<Category> categories = new ArrayList<>();
            post.getCategories().forEach(category ->{
                int flag = categoryService.getCountByName(category.getCategoryName());
                if (flag == 0){
                    //category.setFatherId(fatherId.get());
                    Acate acate = cateToAcate(category);
                    //categoryService.saveCategory(category);
                }else {
                    logger.info("category已经存在！");
                    category = categoryService.getCategoryByName(category.getCategoryName());
                    fatherId.set(category.getCategoryId());
                    category.setCount(0);
                }
                categories.add(category);
            });
            /*for (Category category : post.getCategories()) {
                code = categoryService.getCountByName(category.getCategoryName());
                if (code == 0) {
                    category.setFatherId(fatherId.get());
                    code = categoryService.saveCategory(category);
                    if (code == 0)
                        logger.error("category存入数据库失败！");
                } else {
                    logger.info("category已经存在！");
                    category = categoryService.getCategoryByName(category.getCategoryName());
                    fatherId.set(category.getCategoryId());
                    category.setCount(0);
                }
                logger.info(String.valueOf(fatherId.get()));
                categories.add(category);
            }*/
            post.setCategories(categories);
        }
        //更新tag
        if (post.getTags() != null) {
            logger.info(post.getTags().toString());
            List<Tag> tags = new ArrayList<>();
            post.getTags().forEach(tag -> {
                int flag = tagService.getCountByName(tag.getTagName());
                if (flag == 0){
                    Atag atag = tagToAtag(tag);
                    //tagService.saveTag(tag);
                }else {
                    tag = tagService.getTagByName(tag.getTagName());
                }
                tags.add(tag);
            });
            /*for (Tag tag : post.getTags()) {
                code = tagService.getCountByName(tag.getTagName());
                if (code == 0) {
                    code = tagService.saveTag(tag);
                    if (code == 0)
                        logger.error("tag存入数据库失败！");
                } else {
                    tag = tagService.getTagByName(tag.getTagName());
                }
                tags.add(tag);
            }*/
            post.setTags(tags);
        }

        //更新post
        code = postService.getCountById(post.getPostId());
        if (code == 0) {
            post.setStatus(1);
            //postService.savePost(post);
        }
        //更新post_tag,post_category
        code = 0;
        code = postService.isExistsPostTag(post);
        if (code == 0) {
            //post_tag关系不存在
            //postService.savePostTags(post);
        }
        code = 0;
        code = postService.isExistsPostCate(post);
        if (code == 0) {
            //post_cate关系不存在
            //postService.savePostCategories(post);
        }
        logger.info("解析出的post为：" + post.toString());
        //保存到elasticsearch中
        //postRepositoryService.save(post);

        return jsonResult;
    }


    private JSONObject parseArticle(String fileName, String context) throws ParseException {
        JSONObject rst = new JSONObject();
        context = StringUtils.trim(context);
        Category category = new Category();
        Tag tag = new Tag();
        String frontMatter = StringUtils.substringBefore(context, "---");
        if (StringUtils.isBlank(frontMatter)) {
            context = StringUtils.substringAfter(context, "---");
            frontMatter = StringUtils.substringBefore(context, "---");
        }
        Yaml yaml = new Yaml();
        Map elemts;
        try {
            elemts = yaml.load(frontMatter);
        } catch (Exception e) {
            Date date = new Date(System.currentTimeMillis());
            rst.put("title", StringUtils.substringBeforeLast(fileName, "."));
            rst.put("content", context);
            rst.put("postId", DateUtil.DateToString(date));
            rst.put("publicDate", date);
            rst.put("editDate", date);
            rst.put("saveDate", date);
            category.setCategoryName("默认分类");
            rst.put("category", category);

            return rst;
        }
        //获取标题
        String title = (String) elemts.get("title");
        if (StringUtils.isBlank(title)) {
            title = StringUtils.substringBeforeLast(fileName, ".");
        }
        //获取内容
        //String content=context;
        String content = StringUtils.substringAfter(context, frontMatter);
        if (StringUtils.startsWith(content, "---")) {
            content = StringUtils.substringAfter(content, "---");
        }
        //获取时间

        Date pubilcDate = (Date) elemts.get("date");

        //获取tags
        List<Tag> tags = new ArrayList<>();
        SnowFlake tagSnow = new SnowFlake(2, 3);
        Object arr = elemts.get("tags");
        if (arr == null) {
            tags = null;
        } else if (arr instanceof String) {
            tag.setTagId(tagSnow.nextId());
            tag.setTagName(arr.toString());
            tags.add(tag);
        } else {
            for (String tag1 : (List<String>) arr) {
                if (StringUtils.isBlank(tag1)) {

                } else {
                    tag.setTagId(tagSnow.nextId());
                    tag.setTagName(tag1);
                    tags.add(tag);
                }
            }
        }
        //获取分类
        List<Category> categories = new ArrayList<>();
        List<String> list = new ArrayList<>();
        SnowFlake snowFlake = new SnowFlake(2, 3);
        logger.info("初始化的list: " + list.isEmpty());
        Object cate = elemts.get("categories");
        if (cate == null) {
            categories = null;
        } else if (cate instanceof String) {
            logger.info("-----进入判断是否只有一个标签------");
            category.setCategoryName(cate.toString());
            category.setCategoryId(snowFlake.nextId());
            categories.add(category);
        } else {
            list = (List<String>) elemts.get("categories");
        }
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                logger.info(list.get(i));
                category = new Category();
                category.setCategoryName(list.get(i));
                category.setCategoryId(snowFlake.nextId());
                if (i == 0) {
                    //顶层目录
                    category.setFatherId(-1);
                } else {
                    category.setFatherId(categories.get(i - 1).getCategoryId());
                }
                categories.add(category);
            }
        }

        rst.put("title", title);
        rst.put("postId", DateUtil.DateToString(pubilcDate));
        rst.put("content", content);
        rst.put("publicDate", pubilcDate);
        rst.put("editDate", pubilcDate);
        rst.put("saveDate", pubilcDate);
        rst.put("categories", categories);
        if (tags == null) {
            rst.put("tags", tags);
        } else if (!tags.isEmpty()) {
            rst.put("tags", tags);
        }
        return rst;
    }
    private Acate cateToAcate(Category category){
        Acate acate = new Acate();
        acate.setCateId(category.getCategoryId());
        acate.setCateName(category.getCategoryName());
        acate.setFatherId(category.getFatherId());
        acate.setSaveDate(DateUtil.getNow());
        acate.setStatus(0);
        logger.info("acte: "+acate.toString());
        return acate;
    }
    private Atag tagToAtag(Tag tag){
        Atag atag = new Atag();
        atag.setTagId(tag.getTagId());
        atag.setTagName(tag.getTagName());
        atag.setSaveDate(DateUtil.getNow());
        atag.setStatus(0);
        logger.info("atag: "+atag.toString());
        return atag;
    }
}