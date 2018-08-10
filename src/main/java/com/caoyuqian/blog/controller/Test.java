package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.ResultResponseBody;
import com.caoyuqian.blog.pojo.SysUser;
import com.caoyuqian.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResultResponseBody test(){
        ResultResponseBody resultResponseBody=new ResultResponseBody();
        //SysUser user=new SysUser();
        //user=userService.test();
        resultResponseBody.setResult(userService.test());
        resultResponseBody.setStatus("200");
        resultResponseBody.setMsg("LumiaQian");
        return resultResponseBody;
    }
}
