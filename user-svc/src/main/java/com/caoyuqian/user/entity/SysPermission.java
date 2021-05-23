package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限表 [Data Base Object]
 * @author lumiaqian
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {
	private static final long serialVersionUID =  1798513997747179250L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限标识
     */
    @TableField("perm")
    private String perm;

    /**
     * 请求方法类型（POST/PUT/DELETE/PATCH）
     */
    @TableField("method")
    private String method;

    /**
     * 权限类型 1-路由权限 2-按钮权限
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单模块ID
     */
    @TableField("module_id")
    private Long moduleId;

    /**
     * 创建时间
     */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 拥有资源权限角色ID集合
     */
    @TableField(exist = false)
    private List<Long> roleIds;

}
