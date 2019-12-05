package com.caoyuqian.user.converter;

import com.caoyuqian.user.dto.CreateRoleRequest;
import com.caoyuqian.user.model.po.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: CreateRoleQuest2Role
 * @Package: com.caoyuqian.usersev.converter
 * @Description: CreateRoleQuest2Role
 * @date 2019/12/4 2:32 下午
 **/
@Component
public class CreateRoleRequest2RoleConverter implements Converter<CreateRoleRequest, Role> {
    @Override
    public Role convert(CreateRoleRequest request) {
        Role role = new Role();
        BeanUtils.copyProperties(request,role);
        return role;
    }
}
