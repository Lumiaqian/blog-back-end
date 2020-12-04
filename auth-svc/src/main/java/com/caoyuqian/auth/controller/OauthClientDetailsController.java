package com.caoyuqian.auth.controller;

import com.caoyuqian.auth.dto.CreateOauthClientDetailsDto;
import com.caoyuqian.auth.service.OauthClientDetailsService;
import com.caoyuqian.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author qian
 * @version V1.0
 * @Title: OauthClientDetailsController
 * @Package: com.caoyuqian.auth.controller
 * @Description: OauthClientDetailsController
 * @date 2020/8/3 8:42 下午
 **/
@RestController
@Slf4j
@RequestMapping("oauth/client")
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @PostMapping
    public Result add(@Valid @RequestBody CreateOauthClientDetailsDto dto){
        oauthClientDetailsService.createOauthClientDetails(dto);
        return Result.success();
    }
}
