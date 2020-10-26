package com.caoyuqian.user.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author lumiaqian
 * @date 2020/10/21 14:14
 */
@Data
@TableName("resource")
public class Resource implements Serializable {

	private static final long serialVersionUID =  3553842538866843033L;

	/**
	 * 主键
	 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long resourceId;


	/**
	 * 权限名称
	 */
    @TableField("name")
    private String name;


	/**
	 * 资源路径
	 */
    @TableField("url")
    private String url;


	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;


	/**
	 * 修改时间
	 */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;


}
