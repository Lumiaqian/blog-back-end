package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.entity.RoleResource;
import com.caoyuqian.user.mapper.RoleResourceMapper;
import com.caoyuqian.user.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleResourceServiceImpl
 * @Package: com.caoyuqian.user.service.impl
 * @Description: RoleResourceServiceImpl
 * @date 2020/10/21 2:41 下午
 **/
@Service
@Slf4j
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper,RoleResource> implements RoleResourceService {

    @Override
    public Set<Long> getByRoleId(Long roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId,roleId);
        List<RoleResource> roleResources = list(queryWrapper);
        return roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }
}
