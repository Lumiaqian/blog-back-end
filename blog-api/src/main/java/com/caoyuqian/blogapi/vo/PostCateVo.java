package com.caoyuqian.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: PostCateVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: PostCateVo
 * @date 2020/7/8 3:08 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCateVo {

    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;

    /**
     * 分类id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
}
