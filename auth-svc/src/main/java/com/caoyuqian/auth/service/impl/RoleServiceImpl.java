package com.caoyuqian.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.entity.Role;
import com.caoyuqian.auth.mapper.RoleMapper;
import com.caoyuqian.auth.service.RoleService;
import com.caoyuqian.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleServiceImpl
 * @Package: com.caoyuqian.usersev.service.impl
 * @Description: RoleService
 * @date 2019/12/4 10:28 上午
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> getByUserId(Long userId) {
        Set<Long> roleIds = userRoleService.queryByUserId(userId);

        return (List<Role>) this.listByIds(roleIds);
    }
}
