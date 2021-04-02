package com.caoyuqian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuqian.common.entity.VueRouter;
import com.caoyuqian.user.entity.SysMenu;
import com.caoyuqian.user.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理(SysMenu)表服务接口
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:30
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * @param name 菜单名称
     * @return java.util.List<com.caoyuqian.user.vo.MenuVo>
     * @Description: 菜单
     * @version 0.1.0
     * @author qian
     * @date 2021/4/2 2:08 下午
     * @since 0.1.0
     */
    List<MenuVo> listMenuVO(String name);

    /**
     * @param
     * @return java.util.List<com.caoyuqian.user.vo.RouterVo>
     * @Description: 路由
     * @version 0.1.0
     * @author qian
     * @date 2021/4/2 2:51 下午
     * @since 0.1.0
     */
    List<VueRouter<SysMenu>> listRouterVo();
}
