package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date editDate;

    /**
     * 保存时间
     */
    private Date saveDate;
}
