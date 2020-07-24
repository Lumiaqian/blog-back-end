package com.caoyuqian.user.service;

import com.caoyuqian.user.dto.CreateMenuRequest;
import com.caoyuqian.user.dto.UpdateMenuRequest;
import com.caoyuqian.user.vo.TreeVo;

import java.util.List;

/**
 * @author lumiaqian
 */
public interface MenuService {

    /**
     * @param request
     * @return void
     * @Description: 添加菜单/按钮
     * @version 0.1.0
     * @author qian
     * @date 2020/7/21 11:27 上午
     * @since 0.1.0
     */
    void add(CreateMenuRequest request);

    /**
     * @param request
     * @return void
     * @Description: 更新菜单/权限
     * @version 0.1.0
     * @author qian
     * @date 2020/7/21 7:56 下午
     * @since 0.1.0
     */
    void updateMenu(UpdateMenuRequest request);

    /**
     * @param ids
     * @return void
     * @Description: 批量删除
     * @version 0.1.0
     * @author qian
     * @date 2020/7/21 8:01 下午
     * @since 0.1.0
     */
    void deleteMenuList(List<Long> ids);

    /**
     * @Description: 获取菜单列表¬
     * @param  
     * @version 0.1.0
     * @return com.caoyuqian.user.vo.TreeVo
     * @author qian
     * @date 2020/7/23 7:56 下午
     * @since 0.1.0
     */
    TreeVo findMenus();
}
