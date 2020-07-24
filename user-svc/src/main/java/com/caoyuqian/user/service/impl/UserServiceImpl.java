package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.common.utils.SpringUtil;
import com.caoyuqian.user.converter.User2UserVoConvert;
import com.caoyuqian.user.converter.CreateUserRequest2UserConvert;
import com.caoyuqian.user.dto.CreateUserRequest;
import com.caoyuqian.user.dto.UserQuery;
import com.caoyuqian.user.dto.VerifyPasswordRequest;
import com.caoyuqian.user.mapper.UserMapper;
import com.caoyuqian.user.entity.User;
import com.caoyuqian.user.service.RoleService;
import com.caoyuqian.user.service.UserRoleService;
import com.caoyuqian.user.service.UserService;
import com.caoyuqian.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author qian
 * @version V1.0
 * @Title: UserServiceImpl
 * @Package: com.caoyuqian.usersev.service.impl
 * @Description: UserService
 * @date 2019/11/28 6:03 下午
 **/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private User2UserVoConvert user2UserVoConvert;
    @Autowired
    private CreateUserRequest2UserConvert createUserRequest2UserConvert;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserVo get(Long userId) {
        User user = this.getById(userId);
        return user2UserVoConvert.convert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(CreateUserRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = createUserRequest2UserConvert.convert(request);
        boolean inserts = this.save(user);
        //添加用户拥有的角色
        if (user != null && user.getRoleIds() != null && !user.getRoleIds().isEmpty()) {
            userRoleService.saveBatch(user.getUserId(), user.getRoleIds());
        }
        //throw new SQLTimeoutException();
        return inserts;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(CreateUserRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        return getService().add(request);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean verifyPassword(VerifyPasswordRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", request.getMobile());
        User user = this.getOne(queryWrapper);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo getByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        User user = this.getOne(queryWrapper);
        if (user != null) {
            user.setRoleIds(userRoleService.queryByUserId(user.getUserId()));
            return user2UserVoConvert.convert(user);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<UserVo> getAll(Page page, UserQuery userQuery) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userQuery.getMobile()), "mobile", userQuery.getMobile());
        queryWrapper.eq(StringUtils.isNotBlank(userQuery.getUsername()), "username", userQuery.getUsername());
        IPage<User> iPage = this.page(page, queryWrapper);

        return iPage.convert(user -> user2UserVoConvert.convert(user));
    }

    /**
     * 解决事务失效
     *
     * @return UserServiceImpl
     */
    private UserServiceImpl getService() {
        return SpringUtil.getBean(this.getClass());
    }
}
