package com.caoyuqian.auth.service;

import com.caoyuqian.auth.dto.CreateOauthClientDetailsDto;

/**
 * @author lumiaqian
 */
public interface OauthClientDetailsService {

    /**
     * @param dto
     * @return void
     * @Description: 添加客户端信息
     * @version 0.1.0
     * @author qian
     * @date 2020/8/3 8:15 下午
     * @since 0.1.0
     */
    void createOauthClientDetails(CreateOauthClientDetailsDto dto);
}
