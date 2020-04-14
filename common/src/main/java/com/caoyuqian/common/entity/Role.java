package com.caoyuqian.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: Role
 * @Package: com.caoyuqian.common.entity
 * @Description: TOTO
 * @date 2020/4/14 3:41 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Long roleId;
    private String roleName;
    private String description;
}
