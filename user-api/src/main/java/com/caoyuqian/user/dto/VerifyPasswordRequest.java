package com.caoyuqian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author qian
 * @version V1.0
 * @Title: VerifyPasswordRequest
 * @Package: com.caoyuqian.usersev.model.dto
 * @Description: VerifyPasswordRequest
 * @date 2019/12/5 12:09 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyPasswordRequest {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$",
            message = "手机号格式错误")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    private String password;
}
