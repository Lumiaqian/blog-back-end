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
 * @Title: CreateTagRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreateTagRequest
 * @date 2020/6/30 4:04 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest {

    /**
     * 主键
     */
    private Long tagId;
    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空")
    private String tagName;
    /**
     * 标签的状态1-->正常；0-->删除
     */
    private Integer status;

}
