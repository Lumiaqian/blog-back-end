package com.caoyuqian.blogapi.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author qian
 * @version V1.0
 * @Title: TagQuery
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: TagQuery
 * @date 2020/8/4 9:41 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagQuery {

    /**
     * 主键
     */
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签的状态1-->正常；0-->删除
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
