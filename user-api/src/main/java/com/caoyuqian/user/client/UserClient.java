package com.caoyuqian.user.client;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.entity.Role;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.vo.RoleVo;
import com.caoyuqian.user.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: UserClient
 * @Package: com.caoyuqian.userapi.client
 * @Description: UserClient
 * @date 2019/12/5 2:59 下午
 **/
@FeignClient(name = "user-svc")
public interface UserClient {
    /**
     * 通过手机号获取用户信息接口
     * @param mobile
     * @return
     */
    @GetMapping("/v1/user")
    Result<UserVo> getByMobile(@RequestParam("mobile") @NotBlank String mobile);

    /**
     * 校验密码
     * @param request
     * @return
     */
    @PostMapping("/v1/user/verify")
    Result verifyPassword(@Valid @RequestBody VerifyPasswordRequest request);

    /**
     * 查询用户所拥有的角色
     * @param userId
     * @return
     */
    @GetMapping("/v1/role/user/{userId}")
    Result<List<RoleVo>> getByUserId(@PathVariable(value = "userId") Long userId);
}
