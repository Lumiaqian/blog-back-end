package com.caoyuqian.blogapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdatePostStatus
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: 更新文章状态
 * @date 2020/8/4 9:03 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostStatusRequest {

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空")
    private Long postId;

    /**
     * 文章状态
     */
    @NotNull(message = "文章状态不能为空")
    private Integer status;
}
