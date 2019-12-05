package com.caoyuqian.user.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: UserQuery
 * @Package: com.caoyuqian.usersev.model.query
 * @Description: UserQuery
 * @date 2019/12/5 9:41 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuery {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户名
     */
    private String username;
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
