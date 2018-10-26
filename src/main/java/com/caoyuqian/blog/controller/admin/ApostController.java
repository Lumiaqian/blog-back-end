package com.caoyuqian.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.CategoryService;
import com.caoyuqian.blog.service.PostService;
import com.caoyuqian.blog.service.TagService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.omg.PortableServer.POA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("list")
    public JsonResult getPosts(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        logger.info("pageNo: " + pageNo + " pageSize: " + pageSize);
        try {
            PageInfo<Post> pages= postService.getPostByAdmin(pageNo, pageSize);
            jsonResult.setMessage("获取成功！");
            jsonResult.setData(pages);
            return jsonResult;
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
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
                    tag = new Tag();
                    tag.setTagId(snowFlake.nextId());
                    tag.setTagName(name);
                    //存入数据库
                    tagService.saveTag(tag);
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

}
