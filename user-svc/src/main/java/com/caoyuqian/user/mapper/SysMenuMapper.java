package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.entity.SysMenu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单管理(SysMenu)表数据库访问层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:30
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("<script>" +
            "   select id,name,parent_id,path,component,icon,sort,visible,redirect from sys_menu " +
            "   where visible=1" +
            "   order by sort asc" +
            "</script>")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            // 一对多关联查询拥有菜单访问权限的角色ID集合
            @Result(property = "roles", column = "id", many = @Many(select = "com.caoyuqian.user.mapper.SysRoleMenuMapper.listByMenuId"))
    })
    List<SysMenu> listForRouter();
}
