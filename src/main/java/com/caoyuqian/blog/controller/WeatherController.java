package com.caoyuqian.blog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.City;
import com.caoyuqian.blog.pojo.ResultResponseBody;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.service.impl.CityServiceImpl;
import com.caoyuqian.blog.service.impl.OkHttpService;
import com.caoyuqian.blog.service.impl.UserServiceImpl;
import com.caoyuqian.blog.utils.NetworkUtil;
import com.caoyuqian.blog.utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qian
 * @version V1.0
 * @Title: WeatherController
 * @Package: com.caoyuqian.blog.controller
 * @Description: 通过ip获取物理地址和天气
 * @date 2018/8/12 下午2:47
 **/
@RequestMapping("utils")
@RestController
public class WeatherController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OkHttpService okHttpService;

    @GetMapping("weather/{ip}")
    public JsonResult getWeather(@PathVariable String ip){
        JSONArray weather;
        JsonResult jsonResult=new JsonResult();
        City city=new City();
        logger.info("ip: "+ip);
        //ipv4的正则规则
        String regEx="(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
        Pattern pattern=Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(ip);
        boolean flag=matcher.matches();
        logger.info("ip格式是否正确："+flag);
        if (flag){
            city=okHttpService.getCityByIp(ip);
            if (city!=null){
                weather=okHttpService.getWeatherByCityId(city.getCityId());
                if (weather!=null){
                    jsonResult.setMessage("获取天气成功！");
                    jsonResult.setData(weather);
                }else {
                    jsonResult.setCode(ResultCode.UNKONW_ERROR);
                    jsonResult.setMessage("获取天气失败！");
                }
            }else {
                jsonResult.setCode(ResultCode.UNKONW_ERROR);
                jsonResult.setMessage("ip地址不正确！");
            }
        }else {
            jsonResult.setCode(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("ip地址格式不正确！");
        }
        return jsonResult;
    }

    @GetMapping("weather")
    public JsonResult getWeather(HttpServletRequest request){
        JsonResult jsonResult=new JsonResult();
        String ip= NetworkUtil.getIpAddress(request);
        jsonResult.setData(ip);
        return jsonResult;
    }


}
