package com.caoyuqian.auth.service;

import com.caoyuqian.auth.entity.Menu;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface MenuService {

    /**
     * @param userName
     * @return java.util.List<com.caoyuqian.auth.entity.Menu>
     * @Description: 通过userName获取权限
     * @version 0.1.0
     * @author qian
     * @date 2020/7/23 8:48 下午
     * @since 0.1.0
     */
    List<Menu> findUserPermissionsString(String userName);
}
