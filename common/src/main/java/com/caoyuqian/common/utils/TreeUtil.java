package com.caoyuqian.common.utils;

import com.caoyuqian.common.entity.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: TreeUtil
 * @Package: com.caoyuqian.common.utils
 * @Description: TreeUtil
 * @date 2020/7/22 7:54 下午
 **/
public class TreeUtil {

    private final static Long TOP_NODE_ID = 0L;

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
            if (pid == null || TOP_NODE_ID.equals(pid)) {
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
            if (topNodes.isEmpty()) {
                topNodes.add(node);
            }
        });
        return topNodes;
    }
}
