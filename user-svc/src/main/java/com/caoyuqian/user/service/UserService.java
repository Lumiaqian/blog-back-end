package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuqian.user.dto.*;
import com.caoyuqian.user.entity.User;
import com.caoyuqian.user.vo.UserVo;

/**
 * @author lumiaqian
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param userId 用户id
     * @return UserVo
     */
    UserVo get(Long userId);

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
     *
     * @param page
     * @param userQuery
     * @return page
     */
    IPage<UserVo> getAll(Page<User> page, UserQuery userQuery);

    /**
     * 验证账号和密码
     *
     * @param request
     * @return
     */
    boolean verifyPassword(VerifyPasswordRequest request);

    /**
     * 通过手机号获取用户信息
     *
     * @param mobile
     * @return userVo
     */
    UserDto getByMobile(String mobile);

    /**
     * @param id
     * @return void
     * @Description: 根据id删除用户(注销)
     * @version 0.1.0
     * @author qian
     * @date 2020/7/27 8:52 下午
     * @since 0.1.0
     */
    void deleteById(Long id);

    /**
     * @param mobile
     * @return void
     * @Description: 根据手机号删除用户
     * @version 0.1.0
     * @author qian
     * @date 2020/7/27 8:55 下午
     * @since 0.1.0
     */
    void deleteByMobile(String mobile);

    /**
     * @param request
     * @return void
     * @Description: 更新用户信息
     * @version 0.1.0
     * @author qian
     * @date 2020/7/27 9:49 下午
     * @since 0.1.0
     */
    void updateByUserId(UpdateUserRequest request);

    /**
     * @param
     * @return com.caoyuqian.user.vo.UserVo
     * @Description: 获取当前登录的用户信息
     * @version 0.1.0
     * @author qian
     * @date 2021/5/16 1:55 下午
     * @since 0.1.0
     */
    UserVo getCurrentUser();
}
