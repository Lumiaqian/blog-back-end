package com.caoyuqian.user.converter;

import com.caoyuqian.user.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: Role2ComRole
 * @Package: com.caoyuqian.user.converter
 * @Description: TOTO
 * @date 2019/12/6 3:28 下午
 **/
@Component
public class Role2ComRole implements Converter<Role, com.caoyuqian.commom.entity.Role> {
    @Override
    public com.caoyuqian.commom.entity.Role convert(Role role) {
        com.caoyuqian.commom.entity.Role comRole = new com.caoyuqian.commom.entity.Role();
        BeanUtils.copyProperties(role,comRole);
        return comRole;
    }
}
