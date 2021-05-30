package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色和菜单关联表 [Data Base Object]
 * @author lumiaqian
 */
@Data
@TableName("sys_role_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID =  2687387054359962491L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

}
