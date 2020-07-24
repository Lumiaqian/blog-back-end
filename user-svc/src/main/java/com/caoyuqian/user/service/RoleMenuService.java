package com.caoyuqian.user.service;

import com.caoyuqian.user.dto.CreateRoleMenuRequest;
import com.caoyuqian.user.dto.UpdateRoleMenuRequest;

import java.util.List;


/**
 * @author lumiaqian
 */
public interface RoleMenuService {

    /**
     * @param requests
     * @return void
     * @Description: 添加RoleMenu
     * @version 0.1.0
     * @author qian
     * @date 2020/7/24 11:35 下午
     * @since 0.1.0
     */
    void add(List<CreateRoleMenuRequest> requests);

    /**
     * @param roleId
     * @return void
     * @Description: 根据roleId删除
     * @version 0.1.0
     * @author qian
     * @date 2020/7/24 11:48 下午
     * @since 0.1.0
     */
    void deleteByRoleId(Long roleId);

    /**
     * @param menuId
     * @return void
     * @Description: 根据menuId删除
     * @version 0.1.0
     * @author qian
     * @date 2020/7/24 11:51 下午
     * @since 0.1.0
     */
    void deleteByMenuId(Long menuId);

    /**
     * @Description: 更新
     * @param request
     * @version 0.1.0
     * @return void
     * @author qian
     * @date 2020/7/25 12:00 上午
     * @since 0.1.0
     */
    void updateByRoleId(UpdateRoleMenuRequest request);
}
