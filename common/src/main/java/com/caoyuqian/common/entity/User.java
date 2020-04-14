package com.caoyuqian.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: User
 * @Package: com.caoyuqian.common.entity
 * @Description: TOTO
 * @date 2020/4/14 3:39 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long userId;
    private String mobile;
    private String username;
    private String password;
    private String email;
    /**
     * 用户状态，true为可用
     */
    private Boolean enabled;
    /**
     * 用户账号是否过期，true为未过期
     */
    private Boolean accountNonExpired;
    /**
     * 用户密码是否过期，true为未过期
     */
    private Boolean credentialsNonExpired;
    /**
     * 用户账号是否被锁定，true为未锁定
     */
    private Boolean accountNonLocked;
}
