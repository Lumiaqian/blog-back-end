package com.caoyuqian.auth.service;

import java.util.Set;

/**
 * @author lumiaqian
 */
public interface UserRoleService {

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId
     * @return
     */
    Set<Long> queryByUserId(Long userId);
}
