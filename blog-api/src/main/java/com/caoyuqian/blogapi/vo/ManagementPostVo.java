package com.caoyuqian.blogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: ManagementPostVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: 后台管理Post
 * @date 2020/7/19 1:59 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagementPostVo {
    /**
     * 文章id
     */
    private Long postId;

    /**
     * 文章标题
     */
    private String title;

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
    private List<TagMenuVo> tags;
    /**
     * 分类
     */
    private List<CategoryMenuVo> categories;
}
