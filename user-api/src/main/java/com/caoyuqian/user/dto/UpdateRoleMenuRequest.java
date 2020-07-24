package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateRoleMenuRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: UpdateRoleMenuRequest
 * @date 2020/7/24 11:58 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleMenuRequest {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单/按钮id集合
     */
    private List<Long> menuId;
}
