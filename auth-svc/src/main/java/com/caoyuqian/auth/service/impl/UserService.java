package com.caoyuqian.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.converter.Role2RoleVo;
import com.caoyuqian.auth.entity.User;
import com.caoyuqian.auth.mapper.UserMapper;
import com.caoyuqian.auth.service.RoleService;
import com.caoyuqian.auth.service.UserRoleService;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.client.UserClient;
import com.caoyuqian.user.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService {


    @Autowired
    private RoleService roleService;
    @Autowired
    private Role2RoleVo role2RoleVo;
    @Autowired
    private UserClient userClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.getOne(queryWrapper);
        log.info("user:{}", user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户:" + username + "'不存在");
        }
        //获取角色信息
        List<RoleVo> roles = roleService.getByUserId(user.getUserId()).stream().map(role -> role2RoleVo.convert(role)).collect(Collectors.toList());
        log.info("---------{}", roles);

        roles.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(),
                grantedAuthorities);

    }
}
