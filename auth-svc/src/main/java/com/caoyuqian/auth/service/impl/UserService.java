package com.caoyuqian.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.converter.Role2RoleVo;
import com.caoyuqian.auth.entity.User;
import com.caoyuqian.auth.mapper.UserMapper;
import com.caoyuqian.auth.service.MenuService;
import com.caoyuqian.auth.service.RoleService;
import com.caoyuqian.common.api.Result;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.constant.AuthConstants;
import com.caoyuqian.user.client.UserClient;
import com.caoyuqian.user.dto.UserDto;
import com.caoyuqian.user.vo.ResourceVo;
import com.caoyuqian.user.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private MenuService menuService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User();

        Result<UserDto> userVoResult = userClient.getByMobile(username);
        if (!userVoResult.getCode().equals(Status.SUCCESS.getCode())){
            throw new UsernameNotFoundException("用户:" + username + "'不存在");
        }
        UserDto userDto = userVoResult.getData();
        BeanUtils.copyProperties(userDto, user);
        log.info("user:{}", user);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户:" + username + "'不存在");
        }
        //获取用户角色
        Result<List<RoleVo>> result = userClient.getByUserId(user.getUserId());
        List<RoleVo> roleVos = result.getData();
        List<String> roles = roleVos.stream().map(RoleVo::getRoleName).collect(Collectors.toList());
        //获取权限
        Set<String> authSet = new HashSet<>();
        Result<List<ResourceVo>> listResult = userClient.getResourceByUserId(user.getUserId());
        List<ResourceVo> resourceVos = listResult.getData();
        if (!CollectionUtils.isEmpty(roles)){
            roles.forEach(item -> authSet.add(AuthConstants.AUTHORITY_PREFIX + item));
        }
        authSet.addAll(resourceVos.stream().map(ResourceVo::getUrl).collect(Collectors.toSet()));
        //设置角色资源
        List<GrantedAuthority> finalGrantedAuthorities = AuthorityUtils.createAuthorityList(authSet.toArray(new String[0]));


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(),
                finalGrantedAuthorities);

    }

}
