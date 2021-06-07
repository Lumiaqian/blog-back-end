package com.caoyuqian.user.converter;

import com.caoyuqian.user.entity.User;
import com.caoyuqian.user.vo.UserVo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: User2UserVoConvert
 * @Package: com.caoyuqian.usersev.converter
 * @Description: User2UserVoConvert
 * @date 2019/11/28 7:34 下午
 **/
@Component
public class User2UserVoConvert implements Converter<User, UserVo> {
    @Override
    public UserVo convert(@NotNull User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}
