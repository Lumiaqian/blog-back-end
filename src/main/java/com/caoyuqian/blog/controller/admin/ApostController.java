package com.caoyuqian.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.Atag;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.CategoryService;
import com.caoyuqian.blog.service.PostRepositoryService;
import com.caoyuqian.blog.service.PostService;
import com.caoyuqian.blog.service.TagService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: ApostController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: 管理系统文章api
 * @date 2018/10/10 下午9:33
 **/
@RestController
@Transactional
@RequestMapping("admin/posts")
public class ApostController {
    private final Logger logger = LoggerFactory.getLogger(ApostController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostRepositoryService postRepositoryService;

    @PostMapping("save")
    public JsonResult save(@RequestBody Map map) throws Exception {
        JsonResult jsonResult = new JsonResult();
        Post post = JSONUtil.mapToObj(map, Post.class);
        if (post.getStatus() == 0) {
            logger.info(map.toString());
            String message = "保存成功！";
            jsonResult = saveAndPub(map, message);
        } else {
            jsonResult.setCode(ResultCode.SYS_ERROR);
            jsonResult.setMessage("文章已发布！");
        }

        return jsonResult;
    }

    @PostMapping("pub")
    public JsonResult pub(@RequestBody Map map) throws Exception {
        JsonResult jsonResult = new JsonResult();
        String message = "发表成功！";
        jsonResult = saveAndPub(map, message);
        return jsonResult;
    }
    @PostMapping("edit")
    public JsonResult edit(@RequestBody Map map) throws Exception {
        JsonResult jsonResult = new JsonResult();
        //前端提交过来的需要更新的post
        Post post = JSONUtil.mapToObj(map,Post.class);
        //数据库中原始的post
        Post post1 = postService.getPostById(post.getPostId());
        // logger.info(post.toString());
        //根据前端的categoryI得到相应的category
        JSONArray cateArray = (JSONArray) map.get("cateList");
        List<Long> cateList = JSONObject.parseArray(cateArray.toJSONString(), Long.class);
        //前端提交过来的categories
        List<Category> categories = new ArrayList<Category>();
        cateList.forEach(aLong -> {
            Category category = categoryService.getCateById(aLong);
            categories.add(category);
        });
        //tag
        JSONArray tagArray = (JSONArray) map.get("tagList");
        List<String> tagList = JSONObject.parseArray(tagArray.toJSONString(), String.class);
        //前端提交过来的tag
        List<Tag> tagList1 = new ArrayList<>();
        //前端提交过来的但数据库中不存在的
        List<Tag> emptyTag = new ArrayList<>();
        SnowFlake snowFlake = new SnowFlake(2, 3);//雪花算法生成ID
        tagList.forEach(s -> {
            if (tagService.getCountByName(s)<1){
                //如果前端提交过来的tag数据库中没有，那么就添加到数据库中
                Tag tag = new Tag();
                tag.setTagId(snowFlake.nextId());
                tag.setTagName(s);
                emptyTag.add(tag);
                tagList1.add(tag);
            }else {
                Tag tag = tagService.getTagByName(s);
                tagList1.add(tag);
            }
        });
        logger.info(tagList1.toString());
        post.setCategories(categories);
        post.setTags(tagList1);
        List<Tag> tags = post1.getTags();
        /*List<Category> categories1 = post1.getCategories();*/
        logger.info("数据库中" + tags.toString());
        //更新post_tag:先删除旧的，再新增新的
        if (tagList1.size()>tags.size()){
            //增加了tag
            List<Tag> reduce = getTagReduce(tagList1,tags);
            logger.info("Tags差集："+reduce.toString());
        }else {
            //减少的tag
            List<Tag> reduce = getTagReduce(tags,tagList1);
            logger.info("Tags差集："+reduce.toString());
        }
        ArrayList<HashMap<String,Object>> maps = tagsToMap(post);
        logger.info("需要更新的post_tag："+maps.toString());
        // logger.info("Cates差集："+categoryList.toString());
        //更新
        postService.updatePost(post);
        postService.updatePostTags(maps);

        return jsonResult;
    }
    @GetMapping("list")
    public JsonResult getPosts(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        try {
            PageInfo<Post> pages = postService.getPostByAdmin(pageNo, pageSize);
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(pages);
            return jsonResult;
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @PostMapping("list")
    public JsonResult getPosts(@RequestBody Map map) throws Exception {
        JsonResult jsonResult = new JsonResult();
        Post post = JSONUtil.mapToObj(map, Post.class);
        int pageNo = (int) map.get("pageNo");
        int pageSize = (int) map.get("pageSize");
        logger.info("前台传过来的Post: " + post);
        logger.info(String.valueOf(pageNo));
        logger.info(String.valueOf(pageSize));
        PageInfo<Post> pages = postService.search(post, pageNo, pageSize);
        jsonResult.setData(pages);
        jsonResult.setMessage("获取成功！");
        return jsonResult;
    }

    @GetMapping("draftPosts")
    public JsonResult draftPosts(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        try {
            PageInfo<Post> pages = postService.getDraftPost(pageNo, pageSize);
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(pages);
            return jsonResult;
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @GetMapping("deletedPosts")
    public JsonResult getdeletedPosts(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        try {
            PageInfo<Post> pages = postService.getDeletedPost(pageNo, pageSize);
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(pages);
            return jsonResult;
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @GetMapping("{postId}")
    public JsonResult getPostById(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        Post post = postService.getPostById(postId);
        HashMap<String, Object> ret = new HashMap<>();
        List<String> result = new ArrayList<>();
        post.getCategories().forEach(category -> {
            result.add(String.valueOf(category.getCategoryId()));
        });
        List<String> tags = new ArrayList<>();
        post.getTags().forEach(tag -> {
            tags.add(tag.getTagName());
        });
        ret.put("post", post);
        ret.put("cates", result);
        ret.put("tags", tags);
        jsonResult.setMessage("获取post成功！");
        jsonResult.setData(ret);
        return jsonResult;
    }

    @DeleteMapping("discard/{postId}")
    public JsonResult discardPostById(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        int code = postService.discardPostById(postId);
        if (code > 0) {
            jsonResult.setMessage("已经扔进垃圾箱！");
            Post post = postService.getPostById(postId);
            postRepositoryService.save(post);
        } else {
            jsonResult.setMessage("没有扔进垃圾箱！");
        }
        return jsonResult;
    }

    @DeleteMapping("{postId}")
    public JsonResult deletePostById(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        int code = postService.deletePostById(postId);
        if (code > 0) {
            jsonResult.setMessage("删除成功！");
            Post post = postService.getPostById(postId);
            postRepositoryService.save(post);
        } else {
            jsonResult.setMessage("删除失败！");
        }
        return jsonResult;
    }

    @PutMapping("pub/{postId}")
    public JsonResult pub(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        Post post = new Post();
        post.setPostId(postId);
        post.setStatus(1);
        Post post1 = postService.getPostById(postId);
        if (post1.getPublicDate() == null) {
            post.setPublicDate(DateUtil.getNow());
        }
        else {
            post.setPublicDate(post1.getPublicDate());
        }
        int code = postService.updatePost(post);
        jsonResult.setData(post);
        if (code > 0) {
            jsonResult.setMessage("更新文章状态为发布成功！");
            post = postService.getPostById(postId);
            postRepositoryService.save(post);
        }
        else {
            jsonResult.setMessage("更新文章状态为发布失败！");
        }
        return jsonResult;
    }

    @PutMapping("draft/{postId}")
    public JsonResult draft(@PathVariable String postId) {
        JsonResult jsonResult = new JsonResult();
        Post post = new Post();
        post.setPostId(postId);
        post.setStatus(0);
        post.setEditDate(DateUtil.getNow());
        int code = postService.updatePost(post);
        jsonResult.setData(post);
        if (code > 0) {
            jsonResult.setMessage("更新文章状态为草稿成功！");
            post = postService.getPostById(postId);
            postRepositoryService.save(post);
        }
        else {
            jsonResult.setMessage("更新文章状态为草稿失败！");
        }
        return jsonResult;
    }

    private JsonResult saveAndPub(Map map, String message) throws Exception {
        JsonResult jsonResult = new JsonResult();
        Post post = JSONUtil.mapToObj(map, Post.class);
        int code = postService.getCountByTitile(post.getTitle());
        int flag = postService.getCountById(post.getPostId());

        if (flag > 0) {
            //已经保存过的
            post = parseMap(map);
            logger.info("解析的post: " + post.toString());
//            String postId=postService.getPostByTitle(post.getTitle()).getPostId();
//            post.setPostId(postId);
            Post post1 = postService.getPostById(post.getPostId());
            logger.info("数据库中的post：" + post1.toString());
            List<Tag> tagList = post.getTags();
            List<Tag> tags = post1.getTags();
            List<Category> categoryList = post.getCategories();
            List<Category> categories = post1.getCategories();
            logger.info(tagList.toString());
            logger.info("数据库中" + tags.toString());
            if (tagList.size() > tags.size()) {
                //增加一条记录在post_tag
                //去除tagList中已经在tags中的元素得到数据库post_tag表中没有的的tag元素
                List<Tag> list = new ArrayList<>();
                for (int i = 0; i < tagList.size(); i++) {
                    for (int j = 0; j < tags.size(); j++) {
                        if (tagList.get(i).getTagId() != tags.get(j).getTagId()) {
                            list.add(tagList.get(i));
                        }
                    }
                }
                logger.info(list.toString());
                Post post2 = post;
                post2.setTags(list);
                postService.savePostTags(post2);
            } else if (tagList.size() < tags.size()) {
                //删除多余的post_tag记录
                List<Tag> list = new ArrayList<>();
                for (int i = 0; i < tagList.size(); i++) {
                    for (int j = 0; j < tags.size(); j++) {
                        if (tagList.get(i).getTagId() == tags.get(j).getTagId()) {
                            list.add(tagList.get(i));
                        }
                    }
                }
                logger.info(list.toString());
                Post post2 = post;
                post2.setTags(list);
                postService.deletePostTags(post2);
            } else {
                ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
                for (int i = 0; i < tags.size(); i++) {
                    HashMap<String, Object> saveMap = new HashMap<>();
                    saveMap.put("oldId", tags.get(i).getTagId());
                    saveMap.put("newId", tagList.get(i).getTagId());
                    saveMap.put("postId", post.getPostId());
                    maps.add(saveMap);
                }
                logger.info("maps: " + maps.toString());
                postService.updatePostTags(maps);
            }
            logger.info(categoryList.toString());
            logger.info("数据库中" + categories.toString());
            if (categoryList.size() > categories.size()) {
                //增加一条post_category
                //去除categoryList中在数据表post_category中已存在的数据，添加不存在的数据
                List<Category> list = new ArrayList<>();
                for (int i = 0; i < categoryList.size(); i++) {
                    for (int j = 0; j < categories.size(); j++) {
                        if (categoryList.get(i).getCategoryId() != categories.get(j).getCategoryId()) {
                            list.add(categoryList.get(i));
                        }
                    }
                }
                logger.info(list.toString());
                Post post2 = post;
                post2.setCategories(list);
                postService.savePostCategories(post2);
            } else if (categoryList.size() < categories.size()) {
                List<Category> list = new ArrayList<>();
                for (int i = 0; i < categoryList.size(); i++) {
                    for (int j = 0; j < categories.size(); j++) {
                        if (categoryList.get(i).getCategoryId() == categories.get(j).getCategoryId()) {
                            list.add(categoryList.get(i));
                        }
                    }
                }
                logger.info(list.toString());
                Post post2 = post;
                post2.setCategories(list);
                postService.deletePostCates(post2);
            } else {
                ArrayList<HashMap<String, Object>> cates = new ArrayList<>();
                for (int i = 0; i < categories.size(); i++) {
                    HashMap<String, Object> cateMap = new HashMap<>();
                    cateMap.put("oldId", categories.get(i).getCategoryId());
                    cateMap.put("newId", categoryList.get(i).getCategoryId());
                    cateMap.put("postId", post.getPostId());
                    cates.add(cateMap);
                }
                logger.info("maps: " + cates.toString());
                postService.updatePostCates(cates);
            }
            postService.updatePost(post);
            jsonResult.setMessage(message);
            jsonResult.setData(post.getPostId());
        } else {
            if (code > 0) {
                jsonResult = new JsonResult(ResultCode.SYS_ERROR);
                jsonResult.setMessage("标题已经存在！");
            } else {
                post = savePost(map);
                jsonResult.setMessage(message);
                jsonResult.setData(post.getPostId());
            }
        }
        postRepositoryService.save(post);
        return jsonResult;
    }

    private Post parseMap(Map map) throws Exception {
        Post post = JSONUtil.mapToObj(map, Post.class);
        logger.info("map中的post为：" + post.toString());
        if (!post.getContent().equals("")) {// 文章内容不为空
            //post.setStatus(0);//没发布
            if (post.getStatus() == 1 && (post.getPostId().equals("000") || post.getPostId() == null)) {//如果文章是将要发布的保存时间设为发布时间
                post.setSaveDate(post.getPublicDate());
                post.setPostId(DateUtil.DateToString(post.getPublicDate()));
            } else if (post.getStatus() == 0 && (post.getPostId().equals("000") || post.getPostId() == null)) {
                post.setPostId(DateUtil.DateToString(post.getSaveDate()));
            }
            logger.info("设置完ID:" + post.toString());
            //根据前端的tagId得到相应的tag
            JSONArray tagArray = (JSONArray) map.get("tagList");
            List<String> tagList = JSONObject.parseArray(tagArray.toJSONString(), String.class);
            logger.info(tagList.toString());
            List<Tag> tags = new ArrayList<Tag>();
            SnowFlake snowFlake = new SnowFlake(2, 3);//雪花算法生成ID
            for (String name : tagList) {
                Tag tag = tagService.getTagByName(name);
                if (tag == null) {
                    Atag atag = new Atag();
                    atag.setTagName(name);
                    atag.setTagId(snowFlake.nextId());
                    atag.setSaveDate(DateUtil.getNow());
                    atag.setStatus(0);
                    //存入数据库
                    tagService.saveAtag(atag);
                    tag = new Tag();
                    tag.setTagId(atag.getTagId());
                    tag.setTagName(atag.getTagName());
                }
                //logger.info(tag.toString());
                tags.add(tag);
            }
            logger.info(tags.toString());
            //根据前端的categoryI得到相应的category
            JSONArray cateArray = (JSONArray) map.get("cateList");
            List<Long> cateList = JSONObject.parseArray(cateArray.toJSONString(), Long.class);
            List<Category> categories = new ArrayList<Category>();
            for (Long id : cateList) {
                Category category = categoryService.getCateById(id);
                categories.add(category);
            }
            logger.info(categories.toString());
            post.setCategories(categories);
            post.setTags(tags);
            return post;
        }
        return null;
    }

    private Post savePost(Map map) throws Exception {
        //保存post
        Post post = parseMap(map);
        if (post != null) {
            logger.info(post.toString());
            postService.savePost(post);

            //更新category_post
            postService.savePostCategories(post);
            //更新tog_post
            postService.savePostTags(post);
        }
        return post;
    }

    /*
     * 获取两个tags的差集
     */
    private  List<Tag> getTagReduce(List<Tag> tagList,List<Tag> tags){
        List<Tag> reduce = new ArrayList<>();
        tagList.forEach(tag -> {
            tags.forEach(tag1 -> {
                if (tag.getTagId()!=tag1.getTagId()){
                    reduce.add(tag);
                }
            });
        });
        return reduce;
    }

    /*
     * 获取两个cates的差集
     */
    private List<Category> getCateReduce(List<Category> categoryList,List<Category> categories){
        List<Category> reduce = new ArrayList<>();
        categoryList.forEach(category -> {
            categories.forEach(category1 -> {
                if (category.getCategoryId()!=category1.getCategoryId()){
                    reduce.add(category);
                }
            });
        });
        return reduce;
    }

    /*
     * 获得post_tag
     */
    private ArrayList<HashMap<String,Object>> tagsToMap(Post post){
        ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
        post.getTags().forEach(tag -> {
            HashMap<String, Object> saveMap = new HashMap<>();
            saveMap.put("oldId", tag.getTagId());
            saveMap.put("newId", tag.getTagId());
            saveMap.put("postId", post.getPostId());
            maps.add(saveMap);
        });
        return maps;
    }

}
