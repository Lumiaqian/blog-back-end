package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.UserRoleMapper;
import com.caoyuqian.user.entity.UserRole;
import com.caoyuqian.user.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: UserRoleServiceImpl
 * @Package: com.caoyuqian.usersev.service.impl
 * @Description: TOTO
 * @date 2019/12/4 11:20 上午
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
    @Override
    public boolean saveBatch(Long userId, Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return false;
        }
        removeByUserId(userId);
        Set<UserRole> userRoles = roleIds.stream().map(roleId -> UserRole.builder().userId(userId).roleId(roleId).build()).collect(Collectors.toSet());
        return saveBatch(userRoles);
    }

    @Override
    public boolean removeByUserId(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId,userId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Long> queryByUserId(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserRole> userRoles = list(queryWrapper);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    }
}
