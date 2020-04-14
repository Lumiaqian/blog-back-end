package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Role
 * @Package: com.caoyuqian.user.entity
 * @Description: 角色表
 * @date 2019/11/28 5:50 下午
 **/
@Data
@NoArgsConstructor
@TableName("role")
public class Role {
    @TableId(value = "role_id",type = IdType.ID_WORKER_STR)
    private Long roleId;
    private String roleName;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
