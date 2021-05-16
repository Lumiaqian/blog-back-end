package com.caoyuqian.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lumiaqian
 */

public enum PermTypeEnum {
    /**
     * 路由权限
     */
    ROUTE(1, "路由权限"),
    /**
     * 按钮权限
     */
    BUTTON(2, "按钮权限");

    @Getter
    @Setter
    private Integer value;

    PermTypeEnum(Integer value, String desc) {
        this.value = value;
    }
}
