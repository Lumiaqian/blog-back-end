package com.caoyuqian.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * @author qian
 * @version V1.0
 * @Title: UserVo
 * @Package: com.caoyuqian.usersev.model.vo
 * @Description: TOTO
 * @date 2019/11/28 7:09 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo {
    private String id;
    private String name;
    private String mobile;
    private String username;
    private String deleted;
    private Set<String> roleIds;
    private Date createdTime;
    private Date updatedTime;
}
