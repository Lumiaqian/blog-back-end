package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateRoleRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: 更新角色DTO
 * @date 2020/7/21 10:58 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleRequest {


    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Long roleId;
    /**
     * 名称
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;
    /**
     * 描述
     */
    private String description;
    /**
     * 菜单/按钮权限id集合
     */
    private List<Long> menuIds;
}
