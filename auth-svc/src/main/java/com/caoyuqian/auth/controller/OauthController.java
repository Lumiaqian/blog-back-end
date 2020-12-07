package com.caoyuqian.auth.controller;

import com.caoyuqian.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @version V1.0
 * @Title: UserController
 * @Package: com.caoyuqian.auth.controller
 * @Description: TOTO
 * @date 2019/11/26 3:08 下午
 **/
@RestController
@Slf4j
@RequestMapping("oauth")
public class OauthController {
    @GetMapping("/principal")
    public Result getCurrentUser(Authentication authentication) {
        log.info(authentication.toString());
        return Result.success(authentication.getPrincipal());
    }
}
