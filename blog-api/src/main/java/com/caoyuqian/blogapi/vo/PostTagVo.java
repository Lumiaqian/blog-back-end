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
 * @Title: PostTagVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: PostTagVo
 * @date 2020/7/8 2:52 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostTagVo {
    /**
     * 文章id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;

    /**
     * 标签id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;
}
