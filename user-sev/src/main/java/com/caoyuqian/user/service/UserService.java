package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.dto.UserQuery;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.model.po.User;
import com.caoyuqian.user.model.vo.UserVo;

public interface UserService {

    /**
     * 获取用户
     *
     * @param userId 用户id
     * @return UserVo
     */
    UserVo get(String userId);

    /**
     * 新增用户
     *
     * @param request 用户信息
     * @return Boolean
     */
    boolean add(CreateUserRequest request);
    /**
     * 新增X用户
     *
     * @param request 用户信息
     * @return Boolean
     */
    boolean register(CreateUserRequest request);

    /**
     * 分页查询所有用户信息
     * @param page
     * @param userQuery
     * @return page
     */
    IPage<UserVo> getAll(Page<User> page, UserQuery userQuery);

    /**
     * 验证账号和密码
     * @param request
     * @return
     */
    boolean verifyPassword(VerifyPasswordRequest request);

    /**
     * 通过手机号获取用户信息
     * @param mobile
     * @return userVo
     */
    UserVo getByMobile(String mobile);
}
