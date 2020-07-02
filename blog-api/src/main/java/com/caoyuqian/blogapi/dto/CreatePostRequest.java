package com.caoyuqian.blogapi.dto;

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
 * @Title: CreatePostRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreatePostRequest
 * @date 2020/6/29 2:57 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
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

    /**
     * 发布时间
     */
    private LocalDateTime publicTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    private LocalDateTime createTime;
}
