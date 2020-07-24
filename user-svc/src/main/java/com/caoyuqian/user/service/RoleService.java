package com.caoyuqian.user.service;

import com.caoyuqian.user.dto.CreateRoleRequest;
import com.caoyuqian.user.dto.UpdateRoleRequest;
import com.caoyuqian.user.entity.Role;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface RoleService {
    /**
     * 获取角色
     * @param id
     * @return role
     */
    Role get(Long id);

    /**
     * 获取角色信息通过角色名称
     * @param name
     * @return
     */
    Role getByName(String name);
    /**
     * 获取所有角色
     *
     * @return roles
     */
    List<Role> getAll();
    /**
     * 新增角色
     * @param request
     * @return boolean
     */
    boolean add(CreateRoleRequest request);
    /**
     * 更新角色信息
     *
     * @param request
     */
    boolean update(UpdateRoleRequest request);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    boolean delete(Long id);

    /**
     * 获取用户所拥有的角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);
}
