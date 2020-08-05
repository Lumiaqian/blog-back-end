package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateCategoryStatusRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: UpdateCategoryStatusRequest
 * @date 2020/8/5 9:20 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryStatusRequest {

    /**
     * id
     */
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    /**
     * 状态
     */
    @NotNull(message = "分类状态不能为空")
    private Integer status;
}
