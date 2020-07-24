package com.caoyuqian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.entity.Tree;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.common.utils.TreeUtil;
import com.caoyuqian.user.dto.CreateMenuRequest;
import com.caoyuqian.user.dto.UpdateMenuRequest;
import com.caoyuqian.user.entity.Menu;
import com.caoyuqian.user.mapper.MenuMapper;
import com.caoyuqian.user.service.MenuService;
import com.caoyuqian.user.vo.MenuTree;
import com.caoyuqian.user.vo.TreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuServiceImpl
 * @Package: com.caoyuqian.user.service.impl
 * @Description: MenuServiceImpl
 * @date 2020/7/21 11:28 上午
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CreateMenuRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(request, menu);
        this.save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(UpdateMenuRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(request, menu);
        baseMapper.updateById(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenuList(List<Long> ids) {
        if (ids == null || ids.isEmpty()){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TreeVo findMenus() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Menu::getOrderNum);
        List<Menu> menus = baseMapper.selectList(queryWrapper);

        List<MenuTree> menuTrees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree menuTree = MenuTree.childBuilder()
                    .id(menu.getMenuId())
                    .parentId(menu.getParentId())
                    .label(menu.getMenuName())
                    .component(menu.getComponent())
                    .icon(menu.getIcon())
                    .orderNum(menu.getOrderNum())
                    .path(menu.getPath())
                    .type(menu.getType())
                    .perms(menu.getPerms())
                    .build();
            menuTrees.add(menuTree);
        });

        List<? extends Tree<?>> trees = TreeUtil.build(menuTrees);


        return TreeVo.builder()
                .trees(trees)
                .total(menus.size())
                .build();
    }
}
