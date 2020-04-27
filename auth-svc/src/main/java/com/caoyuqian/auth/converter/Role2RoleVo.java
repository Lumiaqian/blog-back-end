package com.caoyuqian.auth.converter;


import com.caoyuqian.auth.entity.Role;
import com.caoyuqian.user.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Role2RoleVo implements Converter<Role, RoleVo> {
    @Override
    public RoleVo convert(Role role) {

        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role,roleVo);

        return roleVo;
    }
}
