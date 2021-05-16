package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.caoyuqian.common.json.LongArray2StringSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色表 [Data Base Object]
 * @author lumiaqian
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
	private static final long serialVersionUID =  911873310772100066L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;

    /**
     * 删除标识  (0未删除 1已删除)
     */
    @TableField("deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    @JsonSerialize(using = LongArray2StringSerialize.class)
    private List<Long> menuIds;

    @TableField(exist = false)
    @JsonSerialize(using = LongArray2StringSerialize.class)
    private List<Long> permissionIds;
}
