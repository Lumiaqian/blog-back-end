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
 * @Title: TagMenuVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: TagMenuVo
 * @date 2020/7/19 9:31 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagMenuVo {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
}
