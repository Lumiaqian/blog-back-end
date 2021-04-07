package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.SysPermissionMapper;
import com.caoyuqian.user.entity.SysPermission;
import com.caoyuqian.user.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:31
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<String> listPermsByRoleIds(List<Long> roleIds, Integer type) {
        return null;
    }
}
