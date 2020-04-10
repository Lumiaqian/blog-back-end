package com.caoyuqian.user.service;

import java.util.Set;

/**
 * @author lumiaqian
 */
public interface UserRoleService {

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveBatch(Long userId, Set<Long> roleIds);

    /**
     * 删除用户拥有的角色
     *
     * @param userId
     * @return
     */
    boolean removeByUserId(Long userId);

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId
     * @return
     */
    Set<Long> queryByUserId(Long userId);
}
