package com.caoyuqian.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuVo
 * @Package: com.caoyuqian.user.vo
 * @Description: MenuVo
 * @date 2020/7/21 9:50 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuVo implements Serializable {

    private static final long serialVersionUID = -5651789841187416984L;
    /**
     * 菜单/按钮ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    private String menuName;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 对应 Vue组件
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
