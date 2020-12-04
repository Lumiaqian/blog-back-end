package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: UserDTO
 * @Package: com.caoyuqian.user.dto
 * @Description: UserDto
 * @date 2020/10/26 5:31 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long userId;
    private String mobile;
    private String username;
    private String password;
    private Integer deleted;
    private Set<Long> roleIds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
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
