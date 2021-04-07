package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色权限表 [Data Base Object]
 * @author lumiaqian
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {
	private static final long serialVersionUID =  3954847015465623280L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 资源id
     */
    @TableField("permission_id")
    private Long permissionId;

}
