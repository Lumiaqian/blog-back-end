package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.common.utils.SpringUtil;
import com.caoyuqian.user.converter.User2UserVoConvert;
import com.caoyuqian.user.converter.CreateUserRequest2UserConvert;
import com.caoyuqian.user.dto.*;
import com.caoyuqian.user.mapper.UserMapper;
import com.caoyuqian.user.entity.User;
import com.caoyuqian.user.service.RoleService;
import com.caoyuqian.user.service.UserRoleService;
import com.caoyuqian.user.service.UserService;
import com.caoyuqian.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (!checkMobile(request.getMobile())) {
            throw new ServiceException("手机号已存在");
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
    public UserDto getByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }

        user.setRoleIds(userRoleService.queryByUserId(user.getUserId()));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;


    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id == 0) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public void deleteByMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        log.info("mobile:{}", mobile);
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getDeleted, com.caoyuqian.common.constant.Status.IS_DELETE)
                .set(User::getEnabled, false)
                .eq(User::getMobile, mobile);
        baseMapper.update(null, updateWrapper);

    }

    @Override
    public void updateByUserId(UpdateUserRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        //判断是否是新手机号，新手机号需要验证唯一
        if (checkPhoneIsNew(request.getUserId(), request.getMobile())) {
            //判断手机号是否唯一
            if (!checkMobile(request.getMobile())) {
                throw new ServiceException("手机号已存在");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUserId, request.getUserId());

        //更新用户信息
        baseMapper.update(user, updateWrapper);

        //更新用户角色信息
        userRoleService.saveBatch(request.getUserId(), request.getRoleIds());
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

    /**
     * @param mobile
     * @return boolean
     * @Description: 判断手机号是否唯一，true代表唯一
     * @version 0.1.0
     * @author qian
     * @date 2020/7/29 9:52 下午
     * @since 0.1.0
     */
    private boolean checkMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMobile, mobile)
                .eq(User::getDeleted, com.caoyuqian.common.constant.Status.IS_NOT_DELETE);
        User user = baseMapper.selectOne(queryWrapper);
        return null == user;
    }

    /**
     * @param userId
     * @param mobile
     * @return boolean
     * @Description: 校验手机号是否是新的 ，true-->新的，反之false
     * @version 0.1.0
     * @author qian
     * @date 2020/7/30 9:05 下午
     * @since 0.1.0
     */
    private boolean checkPhoneIsNew(Long userId, String mobile) {
        if (userId == null || userId == 0L || StringUtils.isBlank(mobile)) {
            return false;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserId, userId)
                .eq(User::getDeleted, com.caoyuqian.common.constant.Status.IS_NOT_DELETE);
        User user = baseMapper.selectOne(queryWrapper);
        if (mobile.equals(user.getMobile())) {
            return false;
        }
        return true;

    }
}
