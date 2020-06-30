package com.caoyuqian.blogapi.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: PostQuery
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: PostQuery
 * @date 2020/6/29 3:30 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostQuery {

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
     * @return page
     */
    public Page getPage(){
        return new Page(this.current,this.size);
    }
}
