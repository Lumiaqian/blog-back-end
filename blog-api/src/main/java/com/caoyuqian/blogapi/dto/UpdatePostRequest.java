package com.caoyuqian.blogapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdatePostRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: UpdatePostRequest
 * @date 2020/7/3 2:48 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequest {


    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;
    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章的状态1-->已经发布；0-->草稿；-1-->删除
     */
    private Integer status;

    /**
     * 是否开启评论
     */
    private boolean isOpenComment;

}
