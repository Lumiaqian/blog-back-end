package com.caoyuqian.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.dto.RolePermissionDto;
import com.caoyuqian.user.entity.SysRole;
import com.caoyuqian.user.service.SysPermissionService;
import com.caoyuqian.user.service.SysRoleMenuService;
import com.caoyuqian.user.service.SysRolePermissionService;
import com.caoyuqian.user.service.SysRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表(SysRole)表控制层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
@RestController
@RequestMapping("/upm/sysRole")
public class SysRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysRole> page, SysRole sysRole) {
        return Result.success(this.sysRoleService.page(page, new QueryWrapper<>(sysRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysRole sysRole) {
        return Result.success(this.sysRoleService.save(sysRole));
    }

    /**
     * 修改数据
     *
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysRole sysRole) {
        return Result.success(this.sysRoleService.updateById(sysRole));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysRoleService.removeByIds(idList));
    }

    @ApiOperation(value = "角色拥有的菜单ID集合")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/{id}/menu_ids")
    public Result roleMenuIds(@PathVariable("id") Long roleId) {
        return Result.success(sysRoleMenuService.listMenuIds(roleId));
    }

    @ApiOperation(value = "角色拥有的权限ID集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "权限类型", paramType = "query", dataType = "Integer"),
    })
    @GetMapping("/{id}/permission_ids")
    public Result rolePermissionIds(@PathVariable("id") Long roleId, @RequestParam Integer type) {
        return Result.success(sysRolePermissionService.listPermissionIds(roleId, type));
    }


    @ApiOperation(value = "修改角色菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "role", value = "实体JSON对象", required = true, paramType = "body", dataType = "SysRole")
    })
    @PutMapping(value = "/{id}/menu_ids")
    public Result updateRoleMenuIds(
            @PathVariable("id") Long roleId,
            @RequestBody SysRole role) {
        List<Long> menuIds = role.getMenuIds();
        return Result.judge(sysRoleMenuService.update(roleId, menuIds));
    }

    @ApiOperation(value = "修改角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "rolePermission", value = "实体JSON对象", required = true, paramType = "body", dataType = "RolePermissionDTO")
    })
    @PutMapping(value = "/{id}/permission_ids")
    public Result updateRolePermissionIds(
            @PathVariable("id") Long roleId,
            @RequestBody RolePermissionDto rolePermission) {
        rolePermission.setRoleId(roleId);
        return Result.judge(sysRolePermissionService.update(rolePermission));
    }

}
