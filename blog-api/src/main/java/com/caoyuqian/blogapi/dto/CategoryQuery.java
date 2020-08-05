package com.caoyuqian.blogapi.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryQuery
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: CategoryQuery
 * @date 2020/8/5 8:46 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryQuery {

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
    /**
     * 分类的状态1-->正常；0-->删除
     */
    private Integer status;
    /**
     * 分页查询的参数，当前页数
     */
    private long current = 1;
    /**
     * 分页查询的参数，当前页面每页显示的数量
     */
    private long size = 10;
    /**
     * 获取分页参数
     *
     * @return page
     */
    public Page getPage() {
        return new Page(this.current, this.size);
    }
}
