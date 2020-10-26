package com.caoyuqian.user.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author lumiaqian
 * @date 2020/10/21 14:15
 */
@Data
@TableName("role_resource")
public class RoleResource implements Serializable {

	private static final long serialVersionUID =  7719885920540871198L;

	/**
	 * 主键
	 */
	@TableId(value = "id",type = IdType.AUTO)
    private Integer id;


	/**
	 * 资源id
	 */
    @TableField("resource_id")
    private Long resourceId;


	/**
	 * 角色id
	 */
    @TableField("role_id")
    private Long roleId;


}
