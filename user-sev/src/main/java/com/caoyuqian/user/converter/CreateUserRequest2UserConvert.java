package com.caoyuqian.user.converter;

import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.model.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: RegisterUserQuery2UserConvert
 * @Package: com.caoyuqian.usersev.converter
 * @Description: CreateUserQuest2UserConvert
 * @date 2019/12/3 10:19 上午
 **/
@Component
public class CreateUserRequest2UserConvert implements Converter<CreateUserRequest, User> {
    @Override
    public User convert(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request,user);
        return user;
    }
}
