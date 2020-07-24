package com.caoyuqian.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.auth.entity.Menu;
import com.caoyuqian.auth.mapper.MenuMapper;
import com.caoyuqian.auth.service.MenuService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuServiceImpl
 * @Package: com.caoyuqian.auth.service.impl
 * @Description: MenuServiceImpl
 * @date 2020/7/23 8:48 下午
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<Menu> findUserPermissionsString(String userName) {
        if (StringUtils.isBlank(userName)){
            return null;
        }
        return baseMapper.findUserPermissions(userName);
    }
}
