package com.caoyuqian.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.user.entity.SysRolePermission;
import com.caoyuqian.user.service.SysRolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 角色权限表(SysRolePermission)表控制层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:33
 */
@RestController
@RequestMapping("/upm/sysRolePermission")
public class SysRolePermissionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param sysRolePermission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysRolePermission> page, SysRolePermission sysRolePermission) {
        return success(this.sysRolePermissionService.page(page, new QueryWrapper<>(sysRolePermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysRolePermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRolePermission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysRolePermission sysRolePermission) {
        return success(this.sysRolePermissionService.save(sysRolePermission));
    }

    /**
     * 修改数据
     *
     * @param sysRolePermission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysRolePermission sysRolePermission) {
        return success(this.sysRolePermissionService.updateById(sysRolePermission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysRolePermissionService.removeByIds(idList));
    }
}
