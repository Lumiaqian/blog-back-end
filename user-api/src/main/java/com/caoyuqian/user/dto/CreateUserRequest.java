package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: RegisterUserQuery
 * @Package: com.caoyuqian.usersev.model.query
 * @Description: 创建用户
 * @date 2019/12/2 4:31 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @Pattern(regexp = "[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$",
            message = "手机格式错误")
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String username;
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度在8到20个字符")
    private String password;
    /**
     * 用户状态，true为可用
     */
    private Boolean enabled = true;
    /**
     * 用户账号是否过期，true为未过期
     */
    private Boolean accountNonExpired = true;
    /**
     * 用户密码是否过期，true为未过期
     */
    private Boolean credentialsNonExpired = true;
    /**
     * 用户账号是否被锁定，true为未锁定
     */
    private Boolean accountNonLocked = true;
    /**
     * 所拥有的角色
     */
    private Set<String> roleIds;
}
