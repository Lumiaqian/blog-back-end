package com.caoyuqian.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateMenuRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: 更新菜单/按钮权限
 * @date 2020/7/21 7:52 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMenuRequest {

    /**
     * 菜单/按钮ID
     */
    private Long menuId;
    /**
     * 上级菜单ID
     */
    @NotNull(message = "上级菜单ID不能为空")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @NotBlank(message = "菜单/按钮名称不能为空")
    @Size(max = 10,message = "菜单/按钮名称最大长度不能超过10")
    private String menuName;

    /**
     * 菜单URL
     */
    @Size(max = 100,message = "菜单URL最大长度不能超过100")
    private String path;

    /**
     * 对应 Vue组件
     */
    @Size(max = 100,message = "Vue组件最大长度不能超过100")
    private String component;

    /**
     * 权限标识
     */
    @Size(max = 50,message = "权限标识最大长度不能超过50")
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @NotBlank(message = "权限类型不能为空")
    private Integer type;

    /**
     * 排序
     */
    private Integer orderNum;
}
