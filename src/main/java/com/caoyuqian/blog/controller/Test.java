package com.caoyuqian.blog.controller;

import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.service.UserService;
import com.caoyuqian.blog.utils.NetworkUtil;
import com.caoyuqian.blog.utils.UserAgentUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    public JsonResult test(HttpServletRequest request){
        JsonResult jsonResult=new JsonResult();
        HashMap<String,Object> map = new HashMap<>();
        String userAgent = UserAgentUtils.getUserAgent(request);
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        map.put("浏览器组：",UserAgentUtils.getBorderGroup(request));
        map.put("浏览器名字：",UserAgentUtils.getBorderName(request));
        map.put("浏览器类型",UserAgentUtils.getBorderType(request));
        map.put("浏览器生产商：",UserAgentUtils.getBrowserManufacturer(request));
        map.put("浏览器版本：",UserAgentUtils.getBrowserVersion(request));
        map.put("设备生产厂商:",UserAgentUtils.getDeviceManufacturer(request));
        map.put("设备类型:",UserAgentUtils.getDevicetype(request));
        map.put("设备操作系统：",UserAgentUtils.getOs(request));
        map.put("操作系统的名字：",UserAgentUtils.getOsName(request));
        map.put("操作系统的版本号：",UserAgentUtils.getOsVersion(request));
        map.put("操作系统浏览器的渲染引擎:",UserAgentUtils.getBorderRenderingEngine(request));
        map.put("信息",agent.toString());
        jsonResult.setData(map);
        return jsonResult;
    }
}
