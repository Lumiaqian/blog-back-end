package com.caoyuqian.user.vo;

import com.caoyuqian.common.json.LongSet2StringSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: UserVo
 * @Package: com.caoyuqian.usersev.model.vo
 * @Description: UserVo
 * @date 2019/11/28 7:09 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "用户信息")
public class UserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;
    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using = LongSet2StringSerialize.class)
    private Set<Long> roleIds;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "权限信息")
    private List<String> perms ;
}
