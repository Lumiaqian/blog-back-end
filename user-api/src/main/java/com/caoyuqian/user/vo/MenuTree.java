package com.caoyuqian.user.vo;

import com.caoyuqian.common.entity.Tree;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuTreeVo
 * @Package: com.caoyuqian.userapi.vo
 * @Description: MenuTree
 * @date 2020/7/21 8:57 下午
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class MenuTree extends Tree<MenuVo> {

    private String path;

    private String component;

    private String perms;

    private String icon;

    private Integer type;

    private Integer orderNum;

    @Builder(builderMethodName = "childBuilder")
    public MenuTree(Long id, String label, List<Tree<MenuVo>> children, Long parentId, boolean hasParent, boolean hasChildren, String path, String component, String perms, String icon, Integer type, Integer orderNum) {
        super(id, label, children, parentId, hasParent, hasChildren);
        this.path = path;
        this.component = component;
        this.perms = perms;
        this.icon = icon;
        this.type = type;
        this.orderNum = orderNum;
    }
}
