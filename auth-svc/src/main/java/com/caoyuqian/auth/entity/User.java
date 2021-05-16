package com.caoyuqian.auth.entity;

import cn.hutool.core.collection.CollectionUtil;
import com.caoyuqian.common.constant.AuthConstants;
import com.caoyuqian.user.dto.AuthMemberDto;
import com.caoyuqian.user.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


/**
 * 登录用户信息
 */
@Data
@NoArgsConstructor
public class User implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String clientId;

    private Collection<SimpleGrantedAuthority> authorities;

    public User(UserDto user) {
        this.setId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setPassword(AuthConstants.BCRYPT + user.getPassword());
        this.setEnabled(user.getEnabled());
        if (CollectionUtil.isNotEmpty(user.getRoleIds())) {
            authorities = new ArrayList<>();
            user.getRoleIds().forEach(roleId -> authorities.add(new SimpleGrantedAuthority(String.valueOf(roleId))));
        }
    }

    public User(AuthMemberDto member){
        this.setId(member.getId());
        this.setUsername(member.getUsername());
        this.setPassword(AuthConstants.BCRYPT + member.getPassword());
        this.setEnabled( Integer.valueOf(1).equals(member.getStatus()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
