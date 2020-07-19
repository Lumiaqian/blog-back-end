package com.caoyuqian.blogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryMenuVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: CategoryMenuVo
 * @date 2020/7/19 9:32 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryMenuVo {
    /**
     * 主键
     */
    private Long categoryId;

    /**
     * 上级分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
}
