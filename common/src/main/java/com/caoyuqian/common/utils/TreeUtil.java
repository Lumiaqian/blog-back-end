package com.caoyuqian.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.caoyuqian.common.constant.SysAdminConstants;
import com.caoyuqian.common.entity.Tree;
import com.caoyuqian.common.entity.VueRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author qian
 * @version V1.0
 * @Title: TreeUtil
 * @Package: com.caoyuqian.common.utils
 * @Description: TreeUtil
 * @date 2020/7/22 7:54 下午
 **/
public class TreeUtil {

    /**
     * @param nodes
     * @return java.util.List<? extends com.caoyuqian.common.entity.Tree < ?>>
     * @Description: 生成树
     * @version 0.1.0
     * @author qian
     * @date 2020/7/22 8:29 下午
     * @since 0.1.0
     */
    public static <T> List<? extends Tree<?>> build(List<? extends Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        //父级结点
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(node -> {
            //父节点id
            Long pid = node.getParentId();
            if (pid == null || SysAdminConstants.TOP_NODE_ID.equals(pid)) {
                // 判断是否为Top结点
                topNodes.add(node);
                return;
            }
            //遍历List判断是否有node的父节点
            for (Tree<T> n : nodes) {
                Long id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null) {
                        n.initChildren();
                    }
                    n.getChildren().add(node);
                    node.setHasParent(true);
                    n.setHasChildren(true);
                    n.setHasParent(true);
                    return;
                }
            }
        });
        return topNodes;
    }

    /**
     * @param pid
     * @param nodes
     * @return java.util.List<? extends com.caoyuqian.common.entity.Tree < ?>>
     * @Description: 递归生成树
     * @version 0.1.0
     * @author qian
     * @date 2021/5/29 6:08 下午
     * @since 0.1.0
     */
    public static <T> List<? extends Tree<?>> recursionForTree(Long pid, List<? extends Tree<T>> nodes) {
        //父级结点
        List<Tree<T>> list = new ArrayList<>();
        Optional.ofNullable(nodes).orElse(new ArrayList<>())
                .stream()
                .filter(tTree -> tTree.getParentId().equals(pid))
                .forEach(tTree -> {
                    List<Tree<T>> children = (List<Tree<T>>) recursionForTree(tTree.getId(), nodes);
                    if (CollectionUtil.isNotEmpty(children)) {
                        tTree.setChildren(children);
                    }
                    list.add(tTree);
                });
        return list;
    }

    /**
     * 构造前端路由
     *
     * @param routes routes
     * @param <T>    T
     * @return ArrayList<VueRouter < T>>
     */
    public static <T> List<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter<T>> topRoutes = new ArrayList<>();
        routes.forEach(route -> {
            Long parentId = route.getParentId();
            if (parentId == null || SysAdminConstants.TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                Long id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null) {
                        parent.initChildren();
                    }
                    parent.getChildren().add(route);
                    parent.setAlwaysShow(true);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });
        VueRouter<T> router404 = new VueRouter<>();
        router404.setName("404");
        router404.setHidden(true);
        router404.setRedirect("/404");
        router404.setPath("*");
        topRoutes.add(router404);
        return topRoutes;
    }
}
