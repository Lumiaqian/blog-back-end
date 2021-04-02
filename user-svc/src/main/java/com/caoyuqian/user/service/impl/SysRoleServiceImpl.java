package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.user.mapper.SysRoleMapper;
import com.caoyuqian.user.entity.SysRole;
import com.caoyuqian.user.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
