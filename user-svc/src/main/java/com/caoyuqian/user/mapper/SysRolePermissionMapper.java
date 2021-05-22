package com.caoyuqian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.user.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色权限表(SysRolePermission)表数据库访问层
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:33
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * @param moduleId
     * @param roleId
     * @param type
     * @return java.util.List<java.lang.Long>
     * @Description: 查询权限
     * @version 0.1.0
     * @author qian
     * @date 2021/5/22 4:47 下午
     * @since 0.1.0
     */
    @Select({"<script>",
            " SELECT",
            " 	t1.permission_id ",
            " FROM",
            " 	sys_role_permission t1",
            " 	INNER JOIN sys_permission t2 ON t1.permission_id = t2.id ",
            " WHERE 1=1 ",
            " <if test='moduleId !=null '>",
            "    AND t2.module_id = #{moduleId} ",
            " </if>",
            " <if test='roleId !=null '>",
            "   AND t1.role_id = #{roleId} ",
            " </if>",
            " <if test='type !=null '>",
            "    AND t2.type = #{type} ",
            " </if>",
            "</script>"})
    List<Long> listPermissionIds(@Param("moduleId") Long moduleId, @Param("roleId") Long roleId, @Param("type") Integer type);


}
