package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuqian.user.entity.SysPermission;

import java.util.List;

/**
 * 权限表(SysPermission)表服务接口
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:31
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * @param roleIds 角色id
     * @param type
     * @return java.util.List<java.lang.String>
     * @Description: 通过roleId获取对应的权限
     * @version 0.1.0
     * @author qian
     * @date 2021/4/7 3:01 下午
     * @since 0.1.0
     */
    List<String> listPermsByRoleIds(List<Long> roleIds, Integer type);

    /**
     * @param
     * @return boolean
     * @Description: 刷新Redis中的权限信息
     * @version 0.1.0
     * @author qian
     * @date 2021/5/22 5:22 下午
     * @since 0.1.0
     */
    boolean refreshPermissionRolesCache();

    void savePermission(SysPermission sysPermission);
}
