package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联表 [Data Base Object]
 * @author lumiaqian
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID =  2687387054359962491L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Long menuId;

}
