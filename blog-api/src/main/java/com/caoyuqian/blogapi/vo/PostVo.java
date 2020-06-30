package com.caoyuqian.blogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: PostVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: PostVo
 * @date 2020/6/29 3:26 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostVo {

    /**
     * 文章id
     */
    private Integer postId;

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
    private Date publicDate;

    /**
     * 修改时间
     */
    private Date editDate;

    /**
     * 保存时间
     */
    private Date saveDate;
}
