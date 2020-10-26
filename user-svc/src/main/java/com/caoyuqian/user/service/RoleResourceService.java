package com.caoyuqian.user.service;


import java.util.List;
import java.util.Set;

/**
 * @author lumiaqian
 */
public interface RoleResourceService {

    /**
     * @param roleId
     * @return java.util.List<java.lang.Long>
     * @Description: 根据roleId查询出资源id集合
     * @version 0.1.0
     * @author qian
     * @date 2020/10/21 2:42 下午
     * @since 0.1.0
     */
    Set<Long> getByRoleId(Long roleId);
}
