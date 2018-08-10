package com.caoyuqian.blog.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: SysUser
 * @Package: com.caoyuqian.blog.pojo
 * @Description: User系统用户类
 * @date 2018/8/8 下午9:41
 **/
public class SysUser implements Serializable {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private List<Role> roles;//用户拥有的角色

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
