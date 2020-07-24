package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.converter.CreateRoleRequest2RoleConverter;
import com.caoyuqian.user.dto.CreateRoleMenuRequest;
import com.caoyuqian.user.dto.CreateRoleRequest;
import com.caoyuqian.user.dto.UpdateRoleMenuRequest;
import com.caoyuqian.user.dto.UpdateRoleRequest;
import com.caoyuqian.user.mapper.RoleMapper;
import com.caoyuqian.user.entity.Role;
import com.caoyuqian.user.service.RoleMenuService;
import com.caoyuqian.user.service.RoleService;
import com.caoyuqian.user.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private CreateRoleRequest2RoleConverter createRoleRequest2RoleConverter;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public Role get(Long id) {
        return this.getById(id);
    }

    @Override
    public Role getByName(String name) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName,name);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<Role> getAll() {
        return this.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(CreateRoleRequest request) {
        if (request == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Role role = createRoleRequest2RoleConverter.convert(request);
        boolean flag = this.save(role);
        if (flag) {
            //构建RoleMenu
            List<CreateRoleMenuRequest> list = request.getMenuIds().stream().map(id -> {
                assert role != null;
                return CreateRoleMenuRequest.builder()
                        .menuId(id)
                        .roleId(role.getRoleId())
                        .build();
            }).collect(Collectors.toList());
            //保存权限
            roleMenuService.add(list);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateRoleRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Role role = new Role();
        BeanUtils.copyProperties(request,role);

        UpdateRoleMenuRequest updateRoleMenuRequest = UpdateRoleMenuRequest
                .builder()
                .menuId(request.getMenuIds())
                .roleId(request.getRoleId())
                .build();
        roleMenuService.updateByRoleId(updateRoleMenuRequest);
        return this.updateById(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        roleMenuService.deleteByRoleId(id);
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getByUserId(Long userId) {
        Set<Long> roleIds = userRoleService.queryByUserId(userId);

        return (List<Role>) this.listByIds(roleIds);
    }
}
