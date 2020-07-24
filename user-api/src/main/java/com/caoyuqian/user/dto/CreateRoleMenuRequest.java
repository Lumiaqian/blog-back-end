package com.caoyuqian.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateRoleMenuRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: CreateRoleMenuRequest
 * @date 2020/7/24 11:26 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoleMenuRequest {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单/按钮id
     */
    private Long menuId;
}
