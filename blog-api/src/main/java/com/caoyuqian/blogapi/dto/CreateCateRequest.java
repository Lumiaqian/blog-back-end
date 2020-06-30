package com.caoyuqian.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateCateRequest
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CreateCateRequest
 * @date 2020/6/30 4:01 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCateRequest {
    /**
     * 上级分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类的状态1-->正常；0-->删除
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
