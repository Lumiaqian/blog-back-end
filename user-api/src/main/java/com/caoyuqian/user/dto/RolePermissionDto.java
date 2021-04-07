package com.caoyuqian.user.dto;

import lombok.Data;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: RolePermissionDto
 * @Package: com.caoyuqian.user.dto
 * @Description: RolePermissionDto
 * @date 2021/4/7 4:12 下午
 **/
@Data
public class RolePermissionDto {

    private Long roleId;
    private List<Long> permissionIds;
    /**
     * 权限类型 1-路由权限 2-按钮权限
     */
    private Integer type;
    /**
     * 菜单模块ID
     */
    private Long moduleId;
}
