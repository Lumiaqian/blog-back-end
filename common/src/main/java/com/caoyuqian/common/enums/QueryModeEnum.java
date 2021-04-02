package com.caoyuqian.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lumiaqian
 */

public enum QueryModeEnum{
    /**
     * 分页查询
     */
    PAGE("page" ),
    /**
     * 列表查询
     */
    LIST("list"),
    /**
     * 树形列表
     */
    TREE("tree"),
    /**
     * 级联列表 对应级联选择器的下拉格式数据
     */
    CASCADER("cascader"),
    /**
     * 路由列表
     */
    ROUTER("router") ;

    @Getter
    @Setter
    @JsonValue
    private String code;

    QueryModeEnum(String code) {
        this.code=code;
    }

    @JsonCreator
    public static QueryModeEnum getValue(String code){
        for (QueryModeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        // 默认分页查询
        return PAGE;
    }

}
