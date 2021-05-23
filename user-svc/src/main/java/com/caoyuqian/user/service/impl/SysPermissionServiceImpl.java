package com.caoyuqian.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.constant.AuthConstants;
import com.caoyuqian.user.entity.SysPermission;
import com.caoyuqian.user.mapper.SysPermissionMapper;
import com.caoyuqian.user.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:31
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<String> listPermsByRoleIds(List<Long> roleIds, Integer type) {
        return baseMapper.listPermsByRoleIds(roleIds,type);
    }

    @Override
    public boolean refreshPermissionRolesCache() {
        redisTemplate.delete(AuthConstants.PERMISSION_ROLES_KEY);
        List<SysPermission> permissions = baseMapper.listPermissionRoles();
        Map<String, List<String>> permissionRoles = new TreeMap<>();
        Optional.ofNullable(permissions).orElse(new ArrayList<>()).forEach(permission -> {
            // 转换 roleId -> ROLE_{roleId}
            List<String> roles = Optional.ofNullable(permission.getRoleIds())
                    .orElse(new ArrayList<>())
                    .stream()
                    .map(roleId -> AuthConstants.AUTHORITY_PREFIX + roleId)
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(roles)) {
                permissionRoles.put(permission.getMethod() +"_"+ permission.getPerm(), roles);
            }
            redisTemplate.opsForHash().putAll(AuthConstants.PERMISSION_ROLES_KEY, permissionRoles);
        });
        return true;
    }
}
