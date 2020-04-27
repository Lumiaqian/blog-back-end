package com.caoyuqian.auth.service;



import com.caoyuqian.auth.entity.Role;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface RoleService {

    /**
     * 获取用户所拥有的角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(Long userId);
}
