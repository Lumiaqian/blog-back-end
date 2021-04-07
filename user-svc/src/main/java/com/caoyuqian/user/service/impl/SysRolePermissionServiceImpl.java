package com.caoyuqian.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.dto.RolePermissionDto;
import com.caoyuqian.user.entity.SysRolePermission;
import com.caoyuqian.user.mapper.SysRolePermissionMapper;
import com.caoyuqian.user.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限表(SysRolePermission)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:33
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public boolean update(RolePermissionDto rolePermission) {
        boolean result = true;
        List<Long> permissionIds = rolePermission.getPermissionIds();
        Long moduleId = rolePermission.getModuleId();
        Long roleId = rolePermission.getRoleId();
        Integer type = rolePermission.getType();
        List<Long> dbPermissionIds = this.baseMapper.listPermissionIds(moduleId, roleId, type);

        // 删除数据库存在此次提交不存在的
        if (CollectionUtil.isNotEmpty(dbPermissionIds)) {
            List<Long> removePermissionIds = dbPermissionIds.stream().filter(id -> !permissionIds.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(removePermissionIds)) {
                this.remove(new LambdaQueryWrapper<SysRolePermission>().eq(SysRolePermission::getRoleId, roleId)
                        .in(SysRolePermission::getPermissionId, removePermissionIds));
            }
        }

        // 插入数据库不存在的
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            List<Long> insertPermissionIds = permissionIds.stream().filter(id -> !dbPermissionIds.contains(id)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(insertPermissionIds)) {
                List<SysRolePermission> roleMenus = insertPermissionIds.stream().map(id -> new SysRolePermission().setRoleId(roleId).setPermissionId(id)).collect(Collectors.toList());
                result = this.saveBatch(roleMenus);
            }
        }
        return result;
    }

    @Override
    public List<Long> listPermissionIds(Long moduleId, Long roleId, Integer type) {
        return this.baseMapper.listPermissionIds(moduleId, roleId, type);
    }

    @Override
    public List<Long> listPermissionIds(Long roleId, Integer type) {
        return this.baseMapper.listPermissionIds(null, roleId, type);
    }
}
