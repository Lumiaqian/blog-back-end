package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateResourceRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: CreateResourceRequest
 * @date 2020/10/23 4:37 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResourceRequest {


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
