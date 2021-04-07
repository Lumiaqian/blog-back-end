package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuqian.user.dto.RolePermissionDto;
import com.caoyuqian.user.entity.SysRolePermission;

import java.util.List;

/**
 * 角色权限表(SysRolePermission)表服务接口
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:33
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {


    /**
     * @Description: 更新角色权限
     * @param rolePermission
     * @version 0.1.0
     * @return boolean
     * @author qian
     * @date 2021/4/7 4:12 下午
     * @since 0.1.0
     */
    boolean update(RolePermissionDto rolePermission);
    List<Long> listPermissionIds(Long moduleId, Long roleId, Integer type);
    List<Long> listPermissionIds(Long roleId, Integer type);
}
