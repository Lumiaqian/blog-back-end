package com.caoyuqian.user.vo;

import com.caoyuqian.common.entity.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuVo
 * @Package: com.caoyuqian.user.vo
 * @Description: 菜单
 * @date 2021/4/2 11:33 上午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class MenuVo extends Tree<MenuVo> {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 可见性
     */
    private Boolean visible;

    /**
     * 跳转路径
     */
    private String redirect;

}
