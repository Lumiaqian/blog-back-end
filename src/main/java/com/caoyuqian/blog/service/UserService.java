package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.SysUser;

public interface UserService {
    SysUser getUserById(String userId);
    int test();
    int register(SysUser user);
    int saveRoleUser(String userId);
    int updateUser(SysUser user);
}
