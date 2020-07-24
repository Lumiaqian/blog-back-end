package com.caoyuqian.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuqian.auth.entity.Menu;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String userName);
}
