package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: User
 * @Package: com.caoyuqian.usersev.entity
 * @Description: 用户表
 * @date 2019/11/28 3:32 下午
 **/
@Data
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    private String mobile;
    private String username;
    private String password;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
    @TableField(exist = false)
    private Set<String> roleIds;
    @TableLogic
    private String deleted = "N";
}
