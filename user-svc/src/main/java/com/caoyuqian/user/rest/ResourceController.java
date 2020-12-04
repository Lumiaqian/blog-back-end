package com.caoyuqian.user.rest;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.dto.CreateResourceRequest;
import com.caoyuqian.user.dto.UpdateResourceRequest;
import com.caoyuqian.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: ResourceController
 * @Package: com.caoyuqian.user.rest
 * @Description: ResourceController
 * @date 2020/10/21 3:24 下午
 **/
@RestController
@RequestMapping("v1/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public Result add(@RequestBody @Validated CreateResourceRequest createResourceRequest){
        resourceService.saveResource(createResourceRequest);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated UpdateResourceRequest updateResourceRequest){
        resourceService.updateResourceById(updateResourceRequest);
        return Result.success();
    }

    @DeleteMapping("/{resourceId}")
    public Result delete(@NotNull @PathVariable Long resourceId){
        resourceService.deleteResourceById(resourceId);
        return Result.success();
    }
}
