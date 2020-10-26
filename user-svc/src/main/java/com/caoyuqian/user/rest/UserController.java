package com.caoyuqian.user.rest;

import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.dto.UpdateUserRequest;
import com.caoyuqian.user.dto.UserQuery;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.service.ResourceService;
import com.caoyuqian.user.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "body", dataType = "String")
    })
    @PostMapping
    public Result add(@Validated @RequestBody CreateUserRequest request) {

        return Result.success(userService.add(request));
    }

    @ApiOperation(value = "条件查询用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true, paramType = "body", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "body", dataType = "Integer")

    })
    @PostMapping("condition")
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

    @ApiOperation(value = "通过手机号获取用户信息", httpMethod = "GET")
    @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", dataType = "String")
    @GetMapping
    public Result getByMobile(@RequestParam("mobile") @NotBlank String mobile) {
        return Result.success(userService.getByMobile(mobile));
    }

    @ApiOperation(value = "通过手机号删除用户信息", httpMethod = "DELETE")
    @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "path", dataType = "String")
    @DeleteMapping("/{mobile}")
    public Result deleteByMobile(@PathVariable String mobile) {
        userService.deleteByMobile(mobile);
        return Result.success();
    }

    @ApiOperation(value = "修改用户信息", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱",  paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "enabled", value = "用户状态",  paramType = "body", dataType = "Boolean"),
            @ApiImplicitParam(name = "accountNonExpired", value = "用户账号是否过期",  paramType = "body", dataType = "Boolean"),
            @ApiImplicitParam(name = "credentialsNonExpired", value = "用户密码是否过期", paramType = "body", dataType = "Boolean"),
            @ApiImplicitParam(name = "accountNonLocked", value = "用户账号是否被锁定",  paramType = "body", dataType = "Boolean"),
            @ApiImplicitParam(name = "roleIds", value = "所拥有的角色",  paramType = "body", allowMultiple = true, dataType = "String"),
            @ApiImplicitParam(name = "settingId", value = "配置ID",  paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "weibo", value = "微博",  paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "qq", value = "QQ",  paramType = "body", dataType = "String"),
            @ApiImplicitParam(name = "github", value = "github",  paramType = "body", dataType = "String")
    })
    @PutMapping
    public Result update(@Validated @RequestBody UpdateUserRequest request) {
        userService.updateByUserId(request);
        return Result.success();
    }

    @ApiOperation(value = "通过用户ID获取资源信息", httpMethod = "GET")
    @ApiImplicitParam(name = "userId", value = "用ID户", required = true, paramType = "path", dataType = "String")
    @GetMapping("resource/{userId}")
    public Result getResourceByUserId(@NotNull @PathVariable Long userId) {
        return Result.success(resourceService.getByUserId(userId));
    }
}
