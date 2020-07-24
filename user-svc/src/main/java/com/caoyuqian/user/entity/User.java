package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: User
 * @Package: com.caoyuqian.user.entity
 * @Description: 用户表
 * @date 2019/11/28 3:32 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -5156456966185477446L;
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("mobile")
    private String mobile;

    @TableField("user_name")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    /**
     * 用户状态，true为可用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 用户账号是否过期，true为未过期
     */
    @TableField("account_non_expired")
    private Boolean accountNonExpired;

    /**
     * 用户密码是否过期，true为未过期
     */
    @TableField("credentials_non_expired")
    private Boolean credentialsNonExpired;

    /**
     * 用户账号是否被锁定，true为未锁定
     */
    @TableField("account_non_locked")
    private Boolean accountNonLocked;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Set<Long> roleIds;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    /**
     * 最后一次登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 配置ID
     */
    @TableField("setting_id")
    private long settingId;

    /**
     * 微博
     */
    @TableField("weibo")
    private String weibo;

    /**
     * QQ
     */
    @TableField(value = "QQ")
    private String QQ;

    /**
     * github
     */
    @TableField("github")
    private String github;
}
