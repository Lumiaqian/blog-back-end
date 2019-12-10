package com.caoyuqian.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: Role
 * @Package: com.caoyuqian.usersev.model
 * @Description: 角色表
 * @date 2019/11/28 5:50 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role implements Serializable {
    private String id;
    private String name;
    private String description;
}
