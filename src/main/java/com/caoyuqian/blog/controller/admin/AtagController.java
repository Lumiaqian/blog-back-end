package com.caoyuqian.blog.controller.admin;

import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: TagController
 * @Package: com.caoyuqian.blog.controller.admin
 * @Description: 管理系统的标签api
 * @date 2018/10/9 下午8:13
 **/
@RestController
@RequestMapping("admin")
@Transactional
public class AtagController {
    private final Logger logger= LoggerFactory.getLogger(AtagController.class);
    @Autowired
    private TagService tagService;

    @GetMapping("tags/list")
    public JsonResult list(){
        JsonResult jsonResult=new JsonResult();
        List<Tag> tags;
        tags=tagService.getTags();
        jsonResult.setMessage("获取tags列表成功！");
        jsonResult.setData(tags);
        return jsonResult;
    }
}
