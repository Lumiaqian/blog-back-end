package com.caoyuqian.user.client;

import com.caoyuqian.commom.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lumiaqian
 */
@FeignClient(name = "user-role-svc",path = "/v1/role")
public interface RoleClient {

    /**
     * 查询用户所拥有的角色
     * @param userId
     * @return
     */
    @GetMapping("user/{userId}")
    Result getByUserId(@PathVariable(value = "userId") String userId);
}
