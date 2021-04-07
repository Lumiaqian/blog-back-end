package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuqian.user.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务接口
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * @param roleId
     * @param menuIds
     * @return boolean
     * @Description: 修改角色菜单
     * @version 0.1.0
     * @author qian
     * @date 2021/4/7 4:04 下午
     * @since 0.1.0
     */
    boolean update(Long roleId, List<Long> menuIds);

    /**
     * @param roleId
     * @return java.util.List<java.lang.Long>
     * @Description: 根据角色查询对应的菜单
     * @version 0.1.0
     * @author qian
     * @date 2021/4/7 4:53 下午
     * @since 0.1.0
     */
    List<Long> listMenuIds(Long roleId);
}
