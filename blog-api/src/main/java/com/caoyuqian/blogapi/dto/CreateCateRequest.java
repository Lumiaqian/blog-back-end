package com.caoyuqian.blogapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateCateRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreateCateRequest
 * @date 2020/6/30 4:01 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCateRequest {

    /**
     * 主键
     */
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
