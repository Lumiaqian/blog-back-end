package com.caoyuqian.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.mapper.UserMapper;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.entity.Role;
import com.caoyuqian.common.entity.User;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.user.client.UserClient;
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

/**
 * @author qian
 * @version V1.0
 * @Title: UserService
 * @Package: com.caoyuqian.auth.service
 * @Description: TOTO
 * @date 2019/11/26 2:57 下午
 **/
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService {


    @Autowired
    private UserClient roleClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = this.getOne(queryWrapper);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        Result<List<Role>> result = roleClient.getByUserId(user.getId());
        if (!result.getCode().equals(Status.SUCCESS.getCode())){
            throw new ServiceException(result.getMsg());
        }
        result.getData().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        });
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                user.getEnabled(),user.getAccountNonExpired(),user.getCredentialsNonExpired(),user.getAccountNonLocked(),
                grantedAuthorities);
        return securityUser;

    }
}
