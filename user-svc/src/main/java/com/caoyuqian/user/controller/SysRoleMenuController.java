package com.caoyuqian.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.entity.SysRoleMenu;
import com.caoyuqian.user.service.SysRoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表控制层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
@RestController
@RequestMapping("/upm/sysRoleMenu")
public class SysRoleMenuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param sysRoleMenu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysRoleMenu> page, SysRoleMenu sysRoleMenu) {
        return Result.success(this.sysRoleMenuService.page(page, new QueryWrapper<>(sysRoleMenu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysRoleMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysRoleMenu sysRoleMenu) {
        return Result.success(this.sysRoleMenuService.save(sysRoleMenu));
    }

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysRoleMenu sysRoleMenu) {
        return Result.success(this.sysRoleMenuService.updateById(sysRoleMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysRoleMenuService.removeByIds(idList));
    }
}
