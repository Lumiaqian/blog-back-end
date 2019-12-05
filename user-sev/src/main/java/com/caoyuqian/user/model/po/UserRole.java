package com.caoyuqian.user.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: UserRole
 * @Package: com.caoyuqian.usersev.model
 * @Description: 用户角色表
 * @date 2019/11/28 5:54 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_role_relation")
public class UserRole {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String userId;
    private String roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
