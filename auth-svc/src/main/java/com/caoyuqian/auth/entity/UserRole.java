package com.caoyuqian.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private static final long serialVersionUID = -8593514907366356182L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private Long roleId;

}
