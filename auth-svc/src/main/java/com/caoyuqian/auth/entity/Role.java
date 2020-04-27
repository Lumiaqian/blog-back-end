package com.caoyuqian.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Role
 * @Package: com.caoyuqian.auth.entity
 * @Description: 角色信息
 * @date 2020/4/17 5:00 下午
 **/
@Data
@NoArgsConstructor
@TableName("role")
public class Role {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long roleId;
    private String roleName;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
