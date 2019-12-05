package com.caoyuqian.user.rest;

import com.caoyuqian.commom.api.Result;
import com.caoyuqian.user.converter.CreateRoleRequest2RoleConverter;
import com.caoyuqian.user.dto.CreateRoleRequest;
import com.caoyuqian.user.model.po.Role;
import com.caoyuqian.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleController
 * @Package: com.caoyuqian.usersev.rest
 * @Description: TOTO
 * @date 2019/12/4 2:27 下午
 **/
@RestController
@RequestMapping("/v1/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private CreateRoleRequest2RoleConverter createRoleRequest2RoleConverter;

    @PostMapping
    public Result add(@Valid @RequestBody CreateRoleRequest request){
        Role role = createRoleRequest2RoleConverter.convert(request);
        return Result.success(roleService.add(role));
    }
}
