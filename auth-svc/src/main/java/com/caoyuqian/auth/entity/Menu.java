package com.caoyuqian.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Menu
 * @Package: com.caoyuqian.auth.entity
 * @Description: 菜单权限
 * @date 2020/7/20 9:27 下午
 **/
@Data
@TableName("menu")
public class Menu implements Serializable {


    /**
     * 菜单
     */
    public static final Integer TYPE_MENU = 0;
    /**
     * 按钮
     */
    public static final Integer TYPE_BUTTON = 1;
    public static final Long TOP_MENU_ID = 0L;
    private static final long serialVersionUID = -2683403277696926242L;
    /**
     * 菜单/按钮ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单URL
     */
    @TableField("path")
    private String path;

    /**
     * 对应 Vue组件
     */
    @TableField("component")
    private String component;

    /**
     * 权限标识
     */
    @TableField("perms")
    private String perms;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
