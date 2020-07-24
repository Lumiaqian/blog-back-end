package com.caoyuqian.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: UserVo
 * @Package: com.caoyuqian.usersev.model.vo
 * @Description: UserVo
 * @date 2019/11/28 7:09 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String mobile;
    private String username;
    private Integer deleted;
    private Set<Long> roleIds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
