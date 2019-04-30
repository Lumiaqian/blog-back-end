package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.aspect.Log;
import com.caoyuqian.blog.pojo.Atag;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.TagService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.JSONUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: TagController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: 管理系统的标签api
 * @date 2018/10/9 下午8:13
 **/
@RestController
@RequestMapping("admin/tags")
@Transactional
public class AtagController {
    private final Logger logger = LoggerFactory.getLogger(AtagController.class);
    @Autowired
    private TagService tagService;

    @GetMapping("list")
    public JsonResult list() {
        JsonResult jsonResult = new JsonResult();
        List<Tag> tags;
        tags = tagService.getTags();
        jsonResult.setMessage("获取tags列表成功！");
        jsonResult.setData(tags);
        return jsonResult;
    }

    @GetMapping("alist")
    public JsonResult tags(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize) {
        JsonResult jsonResult = new JsonResult();
        try {
            PageInfo<Atag> tags = tagService.getAtags(pageNo, pageSize);
            logger.info("tags: " + tags);
            jsonResult.setMessage("获取tags成功！");
            jsonResult.setData(tags);
        } catch (Exception e) {
            logger.error("服务器异常！");
            e.printStackTrace();
        } finally {
            return jsonResult;
        }
    }

    @PostMapping("tag")
    @Log("添加标签")
    public JsonResult add(@RequestBody Map map) throws Exception {
        JsonResult jsonResult;
        Atag tag = JSONUtil.mapToObj(map, Atag.class);
        logger.info("addTag: " + tag.toString());
        Tag atag = tagService.getTagByName(tag.getTagName());
        if (atag == null) {
            SnowFlake snowFlake = new SnowFlake(2, 3);
            tag.setTagId(snowFlake.nextId());
            logger.info("addedTag: " + tag.toString());
            tagService.saveAtag(tag);
            logger.info("增加" + tag.getTagName() + "标签成功！");
            jsonResult = new JsonResult();
            jsonResult.setMessage("增加" + tag.getTagName() + "标签成功！");
        } else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("标签已存在，请重新输入！");
        }
        return jsonResult;
    }

    @PutMapping("tag")
    @Log("更新标签")
    public JsonResult updateTag(@RequestBody Map map) throws Exception {
        JsonResult jsonResult;
        Atag tag = JSONUtil.mapToObj(map,Atag.class);
        logger.info("updateTag: "+tag.toString());
        if (tag!=null){
            tag.setEditDate(DateUtil.getNow());
            logger.info("updatedTag: "+tag.toString());
            tagService.updateTag(tag);
            logger.info(tag.getTagName()+"标签已更新！");
            jsonResult = new JsonResult();
            jsonResult.setMessage(tag.getTagName()+"标签已更新！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
        }
        return jsonResult;
    }
    @DeleteMapping("tag/{tagId}")
    @Log("删除标签")
    public JsonResult deleteTag(@PathVariable long tagId){
        JsonResult jsonResult;
        //logger.info("tagId: "+tagId);
        Atag atag = new Atag();
        atag.setTagId(tagId);
        atag.setEditDate(DateUtil.getNow());
        atag.setStatus(1);
        int code = tagService.updateTag(atag);
        if (code>0){
            jsonResult=new JsonResult();
            jsonResult.setMessage("删除标签成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("删除标签失败！");
        }
        return  jsonResult;
    }
    @PutMapping("tag/{tagId}")
    @Log("恢复标签")
    public JsonResult recoveryTag(@PathVariable long tagId){
        JsonResult jsonResult;
        Atag atag = new Atag();
        atag.setTagId(tagId);
        atag.setEditDate(DateUtil.getNow());
        atag.setStatus(0);
        int code = tagService.updateTag(atag);
        if (code>0){
            jsonResult=new JsonResult();
            jsonResult.setMessage("恢复标签成功！");
        }else {
            jsonResult = new JsonResult(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("恢复标签失败！");
        }
        return  jsonResult;
    }

}
