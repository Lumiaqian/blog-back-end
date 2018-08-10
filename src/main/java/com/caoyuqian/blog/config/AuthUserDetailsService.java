package com.caoyuqian.blog.config;

import com.caoyuqian.blog.mapper.UserMapper;
import com.caoyuqian.blog.pojo.AuthUser;
import com.caoyuqian.blog.pojo.Role;
import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: AuthUserDetailsService
 * @Package: com.caoyuqian.blog.config
 * @Description: 用户角色验证
 * @date 2018/8/9 下午9:56
 **/
@Component
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userService;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("提交过来的username: "+username);
        AuthUser authUser=new AuthUser();
        SysUser user=new SysUser();
        user=userService.getUserById(username);
        if (user==null){
            throw new UsernameNotFoundException(username);
        }
        authUser.setUserName(user.getUserId());
        authUser.setPassword(user.getPassword());
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (Role role:user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        authUser.setAuthorities(authorities);
        return authUser;
    }
}
