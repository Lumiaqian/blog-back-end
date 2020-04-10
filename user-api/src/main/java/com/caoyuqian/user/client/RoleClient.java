package com.caoyuqian.user.client;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    Result<List<Role>> getByUserId(@PathVariable(value = "userId") Long userId);
}
