package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:32
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


    @Select("<script>" +
            "  select role_id from sys_role_menu where menu_id=#{menuId} " +
            "</script>")
    List<Long> listByMenuId(Long menuId);

    @Select(" SELECT " +
            " 	t1.menu_id  " +
            " FROM " +
            " 	sys_role_menu t1 " +
            " 	INNER JOIN sys_menu t2 ON t1.menu_id = t2.id  " +
            " WHERE role_id =#{roleId}")
    List<Long> listMenuIds(Long roleId);
}
