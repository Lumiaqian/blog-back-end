package com.caoyuqian.blogapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateCateRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: UpdateCateRequest
 * @date 2020/7/3 2:53 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCateRequest {
    /**
     * 主键
     */
    @NotNull(message = "分类id不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    /**
     * 上级分类id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;
    /**
     * 分类的状态1-->正常；0-->删除
     */
    private Integer status;
}
