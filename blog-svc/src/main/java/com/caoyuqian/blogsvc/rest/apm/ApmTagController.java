package com.caoyuqian.blogsvc.rest.apm;

import com.caoyuqian.blogapi.dto.CreateTagRequest;
import com.caoyuqian.blogapi.dto.TagQuery;
import com.caoyuqian.blogapi.dto.UpdateTagRequest;
import com.caoyuqian.blogapi.dto.UpdateTagStatusRequest;
import com.caoyuqian.blogsvc.service.TagService;
import com.caoyuqian.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: ApmTagController
 * @Package: com.caoyuqian.blogsvc.rest.apm
 * @Description: 管理平台tag api
 * @date 2020/8/4 9:37 下午
 **/
@RestController
@RequestMapping("/v1/blog/admin")
public class ApmTagController {
    @Autowired
    private TagService tagService;


    @PostMapping("tag/list")
    public Result getTagsByPage(@RequestBody TagQuery query){
        return Result.success(tagService.getListByPage(query));
    }

    @PostMapping("tag")
    public Result addTag(@Valid @RequestBody CreateTagRequest request){
        return Result.success(tagService.saveOrUpdate(request));
    }

    @PutMapping("tag")
    public Result updateTag(@Valid @RequestBody List<UpdateTagRequest> requests){

        return Result.success(tagService.updateList(requests));
    }

    @PostMapping("tag/status")
    public Result deleteByTagId(@RequestBody UpdateTagStatusRequest request){
        tagService.updateTagStatus(request);
        return Result.success();
    }

    @GetMapping("tag/{tagId}")
    public Result getTagById(@PathVariable Long tagId){
       return Result.success(tagService.getTagById(tagId));
    }
}
