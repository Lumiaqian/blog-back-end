package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.entity.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 权限表(SysPermission)表数据库访问层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:31
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select({
            "<script>",
            " SELECT ",
            " 	perm  ",
            " FROM ",
            " 	sys_permission t1 ",
            " 	LEFT JOIN sys_role_permission t2 ON t1.id = t2.permission_id  ",
            " WHERE ",
            " 	t1.type = #{type}  ",
            " 	AND t2.role_id IN ",
            "       <foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>",
            "           #{roleId}",
            "       </foreach>",
            "</script>"
    })
    List<String> listPermsByRoleIds(@Param("roleIds") List<Long> roleIds, @Param("type") Integer type);

    @Select(" select id, name,perm,method from sys_permission where type=1 ")
    @Results({
            @Result(property = "roleIds", column = "id", many = @Many(select = "com.youlai.admin.mapper.SysRolePermissionMapper.listRoleIds"))
    })
    List<SysPermission> listPermissionRoles();
}
