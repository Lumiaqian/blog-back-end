package com.caoyuqian.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: UserRole
 * @Package: com.caoyuqian.user.entity
 * @Description: 用户角色表
 * @date 2019/11/28 5:54 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -717910672384899262L;
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;


}
