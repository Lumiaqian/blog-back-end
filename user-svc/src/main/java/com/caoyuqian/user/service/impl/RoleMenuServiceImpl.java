package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.dto.CreateRoleMenuRequest;
import com.caoyuqian.user.dto.UpdateRoleMenuRequest;
import com.caoyuqian.user.entity.RoleMenu;
import com.caoyuqian.user.mapper.RoleMenuMapper;
import com.caoyuqian.user.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleMenuServiceImpl
 * @Package: com.caoyuqian.user.service.impl
 * @Description: RoleMenuServiceImpl
 * @date 2020/7/24 11:29 下午
 **/
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(List<CreateRoleMenuRequest> requests) {
        if (requests == null || requests.isEmpty()){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        //添加RoleMenu
        List<RoleMenu> roleMenuList = requests.stream().map(r -> {
            RoleMenu roleMenu = new RoleMenu();
            BeanUtils.copyProperties(r,roleMenu);
            return roleMenu;
        }).collect(Collectors.toList());

        this.saveBatch(roleMenuList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleId(Long roleId) {
        if (roleId == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleMenu::getRoleId,roleId);
        baseMapper.delete(lambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByMenuId(Long menuId) {
        if (menuId == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleMenu::getMenuId,menuId);
        baseMapper.delete(lambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByRoleId(UpdateRoleMenuRequest request) {
        if (request == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        //先删除再添加
        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleMenu::getRoleId,request.getRoleId());
        baseMapper.delete(lambdaQueryWrapper);
        //构建CreateRoleMenu
        List<RoleMenu> list = request.getMenuId().stream()
                .map(id ->{
                    return RoleMenu.builder()
                            .roleId(request.getRoleId())
                            .menuId(id)
                            .build();
                }).collect(Collectors.toList());
        this.saveBatch(list);
    }
}
