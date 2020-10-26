package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateResourceRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: UpdateResourceRequest
 * @date 2020/10/23 4:40 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResourceRequest {

    /**
     * 主键
     */
    @NotNull(message = "resourceId不能为空")
    private Long resourceId;


    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String name;


    /**
     * 资源路径
     */
    @NotBlank(message = "资源路径不能为空")
    private String url;

}
