package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.entity.SysRoleMenu;
import com.caoyuqian.user.mapper.SysRoleMenuMapper;
import com.caoyuqian.user.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public boolean update(Long roleId, List<Long> menuIds) {
        if(roleId == null || roleId == 0L){
            return false;
        }
        // 先删除旧的再添加新的
        this.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId,roleId));
        List<SysRoleMenu> sysRoleMenuList = menuIds.stream().map(menuId -> SysRoleMenu.builder().menuId(menuId).roleId(roleId).build()).collect(Collectors.toList());
        return this.saveBatch(sysRoleMenuList);
    }

    @Override
    public List<Long> listMenuIds(Long roleId) {
        return baseMapper.listMenuIds(roleId);
    }
}
