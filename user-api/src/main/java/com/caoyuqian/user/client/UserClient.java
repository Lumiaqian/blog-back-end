package com.caoyuqian.user.client;

import com.caoyuqian.commom.api.Result;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: UserClient
 * @Package: com.caoyuqian.userapi.client
 * @Description: UserClient
 * @date 2019/12/5 2:59 下午
 **/
@FeignClient(name = "user-sev",path = "/v1/user")
public interface UserClient {
    /**
     * 通过手机号获取用户信息接口
     * @param mobile
     * @return
     */
    @GetMapping
    Result getByMobile(@RequestParam("mobile") @NotBlank String mobile);

    /**
     * 校验密码
     * @param request
     * @return
     */
    @PostMapping("verify")
    Result verifyPassword(@Valid @RequestBody VerifyPasswordRequest request);
}
