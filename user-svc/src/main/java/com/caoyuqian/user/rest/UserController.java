package com.caoyuqian.user.rest;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.dto.UpdateUserRequest;
import com.caoyuqian.user.dto.UserQuery;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: UserController
 * @Package: com.caoyuqian.user.rest
 * @Description: userController
 * @date 2019/12/3 11:06 上午
 **/
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public Result add(@Validated @RequestBody CreateUserRequest request) {

        return Result.success(userService.add(request));
    }

    @PostMapping("condition")
    @PreAuthorize("hasAuthority('user:view')")
    public Result getAll(@RequestBody UserQuery userQuery) {

        return Result.success(userService.getAll(userQuery.getPage(), userQuery));
    }

    @PostMapping("verify")
    public Result verifyPassword(@Validated @RequestBody VerifyPasswordRequest request) {
        if (!userService.verifyPassword(request)) {
            throw new ServiceException("账号或密码错误");
        }
        return Result.success();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:view')")
    public Result getByMobile(@RequestParam("mobile") @NotBlank String mobile) {
        return Result.success(userService.getByMobile(mobile));
    }

    @DeleteMapping("/{mobile}")
    @PreAuthorize("hasAuthority('user:delete')")
    public Result deleteByMobile(@PathVariable String mobile) {
        userService.deleteByMobile(mobile);
        return Result.success();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:update')")
    public Result update(@Validated @RequestBody UpdateUserRequest request){
        userService.updateByUserId(request);
        return Result.success();
    }
}
