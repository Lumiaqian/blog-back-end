package com.caoyuqian.auth.service.impl;

import com.caoyuqian.auth.entity.User;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.utils.RequestUtil;
import com.caoyuqian.user.client.UserClient;
import com.caoyuqian.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: UserService
 * @Package: com.caoyuqian.auth.service
 * @Description: TOTO
 * @date 2019/11/26 2:57 下午
 **/
@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = RequestUtil.getAuthClientId();
        Result<UserDto> userVoResult = userClient.getByMobile(username);
        if (!userVoResult.getCode().equals(Status.SUCCESS.getCode())){
            throw new UsernameNotFoundException("用户:" + username + "'不存在");
        }
        UserDto userDto = userVoResult.getData();
        userDto.setClientId(clientId);
        return new User(userDto);
    }

}
