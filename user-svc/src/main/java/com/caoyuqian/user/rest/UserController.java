package com.caoyuqian.user.rest;

import com.caoyuqian.commom.api.Result;
import com.caoyuqian.commom.api.Status;
import com.caoyuqian.commom.error.ServiceException;
import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.dto.UserQuery;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author qian
 * @version V1.0
 * @Title: UserController
 * @Package: com.caoyuqian.usersev.rest
 * @Description: userController
 * @date 2019/12/3 11:06 上午
 **/
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result add(@Valid @RequestBody CreateUserRequest request){
        boolean flag = false;
        try {
            flag = userService.register(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!flag){
            return Result.fail(Status.FAILURE);
        }
        return Result.success();
    }
    @PostMapping("condition")
    public Result getAll(@RequestBody UserQuery userQuery){

        return Result.success(userService.getAll(userQuery.getPage(),userQuery));
    }
    @PostMapping("verify")
    public Result verifyPassword(@Valid @RequestBody VerifyPasswordRequest request){
        if (!userService.verifyPassword(request)){
            throw new ServiceException("账号或密码错误");
        }
        return Result.success();
    }
    @GetMapping()
    public Result getByMobile(@RequestParam("mobile") @NotBlank String mobile){
        return Result.success(userService.getByMobile(mobile));
    }
}
