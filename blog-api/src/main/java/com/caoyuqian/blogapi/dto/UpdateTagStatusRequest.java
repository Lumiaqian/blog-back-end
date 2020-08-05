package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateTagStatusRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: UpdateTagStatusRequest
 * @date 2020/8/5 9:28 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTagStatusRequest {
    /**
     * id
     */
    @NotNull(message = "标签id不能为空")
    private Long tagId;

    /**
     * 状态
     */
    @NotNull(message = "标签状态不能为空")
    private Integer status;
}
