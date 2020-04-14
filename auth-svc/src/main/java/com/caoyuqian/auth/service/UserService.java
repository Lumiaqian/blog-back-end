package com.caoyuqian.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.entity.User;
import com.caoyuqian.auth.mapper.UserMapper;
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
    private UserClient userClient;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = this.getOne(queryWrapper);
        log.info("user:{}",user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户:" + username + "'不存在");
        }
        //获取角色信息
        Result<List<RoleVo>> result = userClient.getByUserId(user.getUserId());
        log.info("---------{}",result.toString());
        if (!result.getCode().equals(Status.SUCCESS.getCode())){
            throw new ServiceException(result.getMsg());
        }
        result.getData().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                user.getEnabled(),user.getAccountNonExpired(),user.getCredentialsNonExpired(),user.getAccountNonLocked(),
                grantedAuthorities);

    }
}
