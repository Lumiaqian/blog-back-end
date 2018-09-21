package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian
 * @version V1.0
 * @Title: Test
 * @Package: com.caoyuqian.blog.controller
 * @Description: TOTO
 * @date 2018/8/9 下午8:10
 **/
@RestController
public class Test {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public JsonResult test(){
        JsonResult jsonResult=new JsonResult();
        //SysUser user=new SysUser();
        //user=userService.test();
        jsonResult.setData(userService.test());
        jsonResult.setMessage("LumiaQian");
        return jsonResult;
    }
}
