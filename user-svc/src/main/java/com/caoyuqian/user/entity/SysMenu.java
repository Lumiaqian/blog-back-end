package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单管理 [Data Base Object]
 * @author lumiaqian
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
	private static final long serialVersionUID =  5276742768829553411L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 路由路径
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 跳转路径
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 状态：0-禁用 1-开启
     */
    @TableField("visible")
    private Boolean visible;

    /**
     * 创建时间
     */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<Long> roles;
}
