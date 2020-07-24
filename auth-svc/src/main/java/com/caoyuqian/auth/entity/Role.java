package com.caoyuqian.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Role implements Serializable {
    private static final long serialVersionUID = -4406817360513488940L;
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    @TableField("role_name")
    private String roleName;

    @TableField("description")
    private String description;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
