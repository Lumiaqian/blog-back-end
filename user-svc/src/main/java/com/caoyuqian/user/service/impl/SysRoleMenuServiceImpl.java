package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.SysRoleMenuMapper;
import com.caoyuqian.user.entity.SysRoleMenu;
import com.caoyuqian.user.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}
