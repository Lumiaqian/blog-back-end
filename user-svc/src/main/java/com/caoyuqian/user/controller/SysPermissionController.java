package com.caoyuqian.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.entity.SysPermission;
import com.caoyuqian.user.service.SysPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 权限表(SysPermission)表控制层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:31
 */
@RestController
@RequestMapping("/upm/sysPermission")
public class SysPermissionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param sysPermission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysPermission> page, SysPermission sysPermission) {
        return Result.success(this.sysPermissionService.page(page, new QueryWrapper<>(sysPermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysPermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysPermission sysPermission) {
        return Result.success(this.sysPermissionService.save(sysPermission));
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysPermission sysPermission) {
        return Result.success(this.sysPermissionService.updateById(sysPermission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysPermissionService.removeByIds(idList));
    }
}
