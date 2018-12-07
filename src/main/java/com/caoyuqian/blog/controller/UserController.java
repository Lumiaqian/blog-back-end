package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.UserService;
import com.caoyuqian.blog.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: UserController
 * @Package: com.caoyuqian.blog.controller
 * @Description: 用户相关的Controller
 * @date 2018/9/21 下午2:37
 **/
@RestController
@Transactional
public class UserController {
    private final Logger logger= LoggerFactory.getLogger(UserController.class);
   @Autowired
   private UserService userService;

   @PostMapping("register")
    public JsonResult register(@RequestBody SysUser user){
       JsonResult jsonResult=new JsonResult();
       if (user==null){
           jsonResult=new JsonResult(ResultCode.PARAMS_ERROR);
           return jsonResult;
       }else if (StringUtils.isBlank(user.getUserId())||StringUtils.isBlank(user.getPassword())){
           jsonResult=new JsonResult(ResultCode.PARAMS_ERROR);
           return jsonResult;
       }
       try {
           user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
           user.setCreateDate(DateUtil.getNow());
           user.setLastLoginDate(DateUtil.getNow());
           userService.register(user);
           userService.saveRoleUser(user.getUserId());
       } catch (Exception e) {
           logger.error("注册出现异常！"+e);
           jsonResult.setCode(ResultCode.UNKONW_ERROR);
           jsonResult.setMessage("注册出现异常！");
           return jsonResult;
       }
       return jsonResult;
   }

   @GetMapping("lumia/user/info")
    public JsonResult getUserInfo(@Param("username") String username){
       JsonResult jsonResult=new JsonResult();
       SysUser user=userService.getUserById(username);
       user.setPassword(null);
       logger.info(user.toString());
       jsonResult.setData(user);
       return jsonResult;
   }
}
