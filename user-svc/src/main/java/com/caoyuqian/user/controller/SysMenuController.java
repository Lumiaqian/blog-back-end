package com.caoyuqian.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.entity.SysMenu;
import com.caoyuqian.user.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理(SysMenu)表控制层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:30
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysMenu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<SysMenu> page, SysMenu sysMenu) {
        return Result.success(this.sysMenuService.page(page, new QueryWrapper<>(sysMenu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysMenu sysMenu) {
        return Result.success(this.sysMenuService.save(sysMenu));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysMenu sysMenu) {
        return Result.success(this.sysMenuService.updateById(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysMenuService.removeByIds(idList));
    }

    @GetMapping("router")
    public Result listRouter(){
        return Result.success(this.sysMenuService.listRouterVo());
    }
}
