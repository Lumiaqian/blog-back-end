package com.caoyuqian.blog.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: SysUser
 * @Package: com.caoyuqian.blog.pojo
 * @Description: User系统用户类
 * @date 2018/8/8 下午9:41
 **/
@Getter
@Setter
@Data
public class SysUser implements Serializable {
    private String userId;
    private String userName;
    private String password;
    private String email;
    //创建时间
    private Date createDate;
    //最后一次登录时间
    private Date lastLoginDate;
    //配置ID
    private long settingId;
    private Setting setting;
    //用户拥有的角色
    private List<Role> roles;
    //微博
    private String weibo;
    //QQ
    private String QQ;
    //github
    private String github;


}
