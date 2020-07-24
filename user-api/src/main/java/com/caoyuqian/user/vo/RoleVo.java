package com.caoyuqian.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleVo
 * @Package: com.caoyuqian.user.vo
 * @Description: RoleVo
 * @date 2020/4/14 4:17 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    private String roleName;
    private String description;
}
