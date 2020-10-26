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
}
