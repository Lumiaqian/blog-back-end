package com.caoyuqian.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.caoyuqian.blog.pojo.*;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.*;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

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
    @Autowired
    IQiniuUploadFileService iQiniuUploadFileService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private UserService userService;
    @Value("${qiniu.path}")
    private String path;
    @Autowired
    private QiniuImageService qiniuImageService;
    @Autowired
    private PostRepositoryService postRepositoryService;

    @PostMapping("upload")
    public ResponseEntity<JsonResult> upload(MultipartFile file) throws Exception {
        JsonResult jsonResult;
        // logger.info(constantQiniu.toString());
        if (Objects.isNull(file) || file.isEmpty()) {
            //上传文件为空
            logger.error("文件为空！");
            jsonResult = new JsonResult(ResultCode.File_Empty);
            return new ResponseEntity<>(jsonResult, HttpStatus.BAD_REQUEST);
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info(suffixName);
        if (!suffixName.equals(".md")) {
            //文件格式错误
            logger.error("文件格式错误！");
            jsonResult = new JsonResult(ResultCode.File_Format_ERROR);
            return new ResponseEntity<>(jsonResult, HttpStatus.BAD_REQUEST);
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
        //更新数据库中的内容
        int code = 0;
        //更新category
        AtomicLong fatherId = new AtomicLong();
        fatherId.set(-1);
        if (post.getCategories() != null) {
            logger.info(post.getCategories().toString());
            List<Category> categories = new ArrayList<>();
            post.getCategories().forEach(category -> {
                int flag = categoryService.getCountByName(category.getCategoryName());
                if (flag == 0) {
                    //category.setFatherId(fatherId.get());
                    Acate acate = cateToAcate(category);
                    //categoryService.saveCategory(category);
                    categoryService.saveCate(acate);
                } else {
                    logger.info("category已经存在！");
                    category = categoryService.getCategoryByName(category.getCategoryName());
                    fatherId.set(category.getCategoryId());
                    category.setCount(0);
                }
                categories.add(category);
            });
            post.setCategories(categories);
        }
        //更新tag
        if (post.getTags() != null) {
            logger.info(post.getTags().toString());
            List<Tag> tags = new ArrayList<>();
            post.getTags().forEach(tag -> {
                int flag = tagService.getCountByName(tag.getTagName());
                if (flag == 0) {
                    Atag atag = tagToAtag(tag);
                    //tagService.saveTag(tag);
                    tagService.saveAtag(atag);
                } else {
                    tag = tagService.getTagByName(tag.getTagName());
                }
                tags.add(tag);
            });
            post.setTags(tags);
        }

        //更新post
        code = postService.getCountById(post.getPostId());
        if (code == 0) {
            post.setStatus(1);
            postService.savePost(post);
        }
        //更新post_tag,post_category
        code = 0;
        code = postService.isExistsPostTag(post);
        if (code == 0) {
            //post_tag关系不存在
            postService.savePostTags(post);
        }
        code = 0;
        code = postService.isExistsPostCate(post);
        if (code == 0) {
            //post_cate关系不存在
            postService.savePostCategories(post);
        }
        logger.info("解析出的post为：" + post.toString());
        //保存到elasticsearch中
        postRepositoryService.save(post);
        //保存到服务器中
        String key = post.getTitle();
        Response response = iQiniuUploadFileService.uploadFile(file.getInputStream(), key);
        //Response response = iQiniuUploadFileService.uploadFile((File) file);
        logger.info(JSONObject.toJSON(response).toString());
        logger.info(JSONObject.toJSON(response.bodyString()).toString());
        logger.info(JSONObject.toJSON(response.url()).toString());
       /* try {
            byte[] bytes = file.getBytes();
            //如果没有post/文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER + "/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("服务器异常");
        }*/
        jsonResult = new JsonResult();
        jsonResult.setMessage("解析成功！");
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @PostMapping("avatar")
    public ResponseEntity<JsonResult> setAvatar(MultipartFile img, @RequestParam String userId) throws IOException {
        JsonResult jsonResult;
        logger.info("传入的文件参数：{}", JSON.toJSONString(img, true));
        logger.info(img.getOriginalFilename());
        logger.info(userId);
        String imgData = Base64.encodeBase64String(img.getBytes());
        Setting setting = new Setting();
        SnowFlake snow = new SnowFlake(2, 3);
        SysUser sysUser = userService.getUserById(userId);
        if (sysUser.getSettingId()==0){
            //没有头像信息，则新建
            setting.setId(snow.nextId());
            setting.setAvatar(imgData);
            sysUser.setSettingId(setting.getId());
            userService.updateUser(sysUser);
            settingService.saveSetting(setting);
        }else {
            //如果用户已经有了头像信息，那么只需更新即可
            setting = sysUser.getSetting();
            setting.setId(sysUser.getSettingId());
            setting.setAvatar(imgData);
            settingService.updateSetting(setting);
        }
        jsonResult = new JsonResult();
        jsonResult.setData(setting);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @PostMapping("qiniu/images")
    public ResponseEntity<JsonResult> putImages(MultipartFile file) throws IOException {
        logger.info(file.toString());
        logger.info("传入的文件参数：{}", JSON.toJSONString(file, true));
        SnowFlake snow = new SnowFlake(2, 3);
        QiniuImage qiniuImage = new QiniuImage();
        qiniuImage.setId(snow.nextId());
        qiniuImage.setName(file.getOriginalFilename());
        //保存到服务器中
        try {
            String key = file.getOriginalFilename();

            String url = iQiniuUploadFileService.uploadImg(file.getInputStream(), key);
            qiniuImage.setUrl(url);
            //logger.info(qiniuImage.toString());
            qiniuImageService.save(qiniuImage);
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
            logger.error(r.bodyString());

        }
        JsonResult jsonResult = new JsonResult();
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    @GetMapping("qiniu/images")
    public ResponseEntity<JsonResult> getImages(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        PageInfo<QiniuImage> pageInfo = qiniuImageService.getQinius(pageNo,pageSize);
        jsonResult.setData(pageInfo);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
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

    private Acate cateToAcate(Category category) {
        Acate acate = new Acate();
        acate.setCateId(category.getCategoryId());
        acate.setCateName(category.getCategoryName());
        acate.setFatherId(category.getFatherId());
        acate.setSaveDate(DateUtil.getNow());
        acate.setStatus(0);
        logger.info("acte: " + acate.toString());
        return acate;
    }

    private Atag tagToAtag(Tag tag) {
        Atag atag = new Atag();
        atag.setTagId(tag.getTagId());
        atag.setTagName(tag.getTagName());
        atag.setSaveDate(DateUtil.getNow());
        atag.setStatus(0);
        logger.info("atag: " + atag.toString());
        return atag;
    }
}
