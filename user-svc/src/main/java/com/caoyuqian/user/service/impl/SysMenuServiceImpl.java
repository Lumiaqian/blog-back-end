package com.caoyuqian.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.entity.RouterMeta;
import com.caoyuqian.common.entity.VueRouter;
import com.caoyuqian.common.utils.TreeUtil;
import com.caoyuqian.user.entity.SysMenu;
import com.caoyuqian.user.mapper.SysMenuMapper;
import com.caoyuqian.user.service.SysMenuService;
import com.caoyuqian.user.vo.MenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单管理(SysMenu)表服务实现类
 *
 * @author lumiaqian
 * @since 2021-03-30 10:06:30
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<MenuVo> listMenuVO(String name) {
        LambdaQueryWrapper<SysMenu> baseQuery = new LambdaQueryWrapper<SysMenu>()
                .orderByAsc(SysMenu::getSort)
                .orderByDesc(SysMenu::getUpdateTime)
                .orderByDesc(SysMenu::getCreateTime)
                .like(StrUtil.isNotBlank(name), SysMenu::getName, name);
        List<SysMenu> menuList = this.list(baseQuery);
        List<MenuVo> menuVoList = menuList.stream().map(sysMenu -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(sysMenu,menuVo);
            return menuVo;
        }).collect(Collectors.toList());
        TreeUtil.build(menuVoList);
        return menuVoList;
    }

    @Override
    public List<VueRouter<SysMenu>> listRouterVo() {
        List<SysMenu> menuList = baseMapper.listForRouter();
        List<VueRouter<SysMenu>> routerVos = menuList.stream().map(sysMenu -> {
            VueRouter<SysMenu> vueRouter = new VueRouter<>();
            BeanUtils.copyProperties(sysMenu,vueRouter);
            vueRouter.setMeta(RouterMeta.builder().icon(sysMenu.getIcon())
                    .roles(sysMenu.getRoles())
                    .title(sysMenu.getName()).build());
            vueRouter.setHidden(!sysMenu.getVisible());
            return vueRouter;
        }).collect(Collectors.toList());
        return TreeUtil.buildVueRouter(routerVos);
    }
}
