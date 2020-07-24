package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleMenu
 * @Package: com.caoyuqian.user.entity
 * @Description: 角色菜单权限关联表
 * @date 2020/7/20 9:44 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -1705300740915772935L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("menu_id")
    private Long menuId;
}