package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.RoleMapper;
import com.caoyuqian.user.entity.Role;
import com.caoyuqian.user.service.RoleService;
import com.caoyuqian.user.service.UserRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Role get(Long id) {
        return this.getById(id);
    }

    @Override
    public Role getByName(String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<Role> getAll() {
        return this.list();
    }

    @Override
    public boolean add(Role role) {
        return this.save(role);
    }

    @Override
    public boolean update(Role role) {
        return this.update(role);
    }

    @Override
    public boolean delete(Long id) {
        return this.delete(id);
    }

    @Override
    public List<Role> getByUserId(Long userId) {
        Set<Long> roleIds = userRoleService.queryByUserId(userId);

        return (List<Role>) this.listByIds(roleIds);
    }
}
