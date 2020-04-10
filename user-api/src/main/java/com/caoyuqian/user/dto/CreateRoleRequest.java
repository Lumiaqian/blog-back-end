package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateRoleQuest
 * @Package: com.caoyuqian.usersev.model.dto
 * @Description: TOTO
 * @date 2019/12/4 2:30 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleRequest {
    /**
     * 名称
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;
    /**
     * 描述
     */
    private String description;
}
