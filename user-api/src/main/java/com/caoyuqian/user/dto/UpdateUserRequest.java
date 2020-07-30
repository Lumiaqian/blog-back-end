package com.caoyuqian.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: UpdateUserRequest
 * @Package: com.caoyuqian.user.dto
 * @Description: UpdateUserRequest
 * @date 2020/7/27 9:17 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @Pattern(regexp = "[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$",
            message = "手机格式错误")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;


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
    /**
     * 所拥有的角色
     */
    private Set<Long> roleIds;
    /**
     * 配置ID
     */
    private long settingId;

    /**
     * 微博
     */
    private String weibo;

    /**
     * QQ
     */
    private String qq;

    /**
     * github
     */
    private String github;
}
