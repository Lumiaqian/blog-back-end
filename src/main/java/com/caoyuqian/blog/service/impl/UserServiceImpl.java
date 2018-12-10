package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.UserMapper;
import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: UserServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: UserService
 * @date 2018/8/9 下午9:48
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int test() {
        return userMapper.test();
    }

    @Override
    public int register(SysUser user) {
        return userMapper.register(user);
    }

    @Override
    public int saveRoleUser(String userId) {
        return userMapper.saveRoleUser(userId);
    }

    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }
}
