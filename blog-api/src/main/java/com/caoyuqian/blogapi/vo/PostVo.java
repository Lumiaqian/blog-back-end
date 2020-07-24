package com.caoyuqian.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: PostVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: PostVo
 * @date 2020/7/8 2:39 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostVo {

    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;

    /**
     * 文章标题
     */
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
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 分类
     */
    private List<String> categories;
}
