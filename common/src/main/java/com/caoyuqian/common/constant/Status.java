package com.caoyuqian.common.constant;


/**
 * @author lumiaqian
 */
public interface Status {
    /**
     * 已经发布状态
     */
    Integer PUBLIC = 1;
    /**
     * 存在草稿箱状态
     */
    Integer DRAFT = 0;
    /**
     * 被删除的状态
     */
    Integer DELETE = -1;

    /**
     * 逻辑删除
     */
    Integer IS_DELETE = 1;

    /**
     * 逻辑未删除
     */
    Integer IS_NOT_DELETE = 0;


}
