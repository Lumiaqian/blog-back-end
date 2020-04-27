package com.caoyuqian.user.rest;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.user.converter.CreateRoleRequest2RoleConverter;
import com.caoyuqian.user.converter.Role2RoleVo;
import com.caoyuqian.user.dto.CreateRoleRequest;
import com.caoyuqian.user.entity.Role;
import com.caoyuqian.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleController
 * @Package: com.caoyuqian.usersev.rest
 * @Description: role API
 * @date 2019/12/4 2:27 下午
 **/
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private CreateRoleRequest2RoleConverter createRoleRequest2RoleConverter;
    @Autowired
    private Role2RoleVo role2ComRole;

    @PostMapping("role")
    public Result add(@Valid @RequestBody CreateRoleRequest request){
        Role role = createRoleRequest2RoleConverter.convert(request);
        return Result.success(roleService.add(role));
    }
    @GetMapping("role/{userId}")
    public Result getByUserId(@PathVariable Long userId){

        return Result.success(roleService.getByUserId(userId).stream().map(role -> role2ComRole.convert(role)));
    }
}
