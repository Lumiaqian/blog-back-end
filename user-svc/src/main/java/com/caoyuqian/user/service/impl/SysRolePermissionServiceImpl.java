package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.SysRolePermissionMapper;
import com.caoyuqian.user.entity.SysRolePermission;
import com.caoyuqian.user.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限表(SysRolePermission)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:33
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

}
