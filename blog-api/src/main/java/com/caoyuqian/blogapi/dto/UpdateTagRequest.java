package com.caoyuqian.blogapi.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateTagRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: UpdateTagRequest
 * @date 2020/7/7 2:23 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTagRequest {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "标题id不能为空")
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签的状态1-->正常；0-->删除
     */
    private Integer status;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    private LocalDateTime createTime;
}
