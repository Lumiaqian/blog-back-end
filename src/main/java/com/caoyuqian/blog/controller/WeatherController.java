package com.caoyuqian.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.City;
import com.caoyuqian.blog.pojo.Device;
import com.caoyuqian.blog.pojo.result.JsonResult;
import com.caoyuqian.blog.pojo.result.ResultCode;
import com.caoyuqian.blog.rabbitmq.DeviceSender;
import com.caoyuqian.blog.service.DeviceService;
import com.caoyuqian.blog.service.impl.OkHttpService;
import com.caoyuqian.blog.utils.NetworkUtil;
import com.caoyuqian.blog.utils.OkHttpUtil;
import com.caoyuqian.blog.utils.UserAgentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OkHttpService okHttpService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceSender deviceSender;

    @GetMapping("weather/{ip}")
    public JsonResult getWeather(@PathVariable String ip,HttpServletRequest r) {
        JsonResult jsonResult = getWeatherByIP(ip,r);
        return jsonResult;
    }

    @GetMapping("ip")
    public JsonResult getWeather(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String ip = NetworkUtil.getIpAddress(request);
        JSONObject jsonObject = getIP();
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("ip", ip);
        hashMap.put("data", jsonObject);
        jsonResult.setData(hashMap);
        return jsonResult;
    }

    @GetMapping("weather")
    public JsonResult getWeatherByIP(HttpServletRequest request) {
        JsonResult jsonResult;
        JSONObject jsonObject = getIP();
        // String ip = (String) jsonObject.get("cip");
        String ip = NetworkUtil.getIpAddress(request);
        jsonResult = getWeatherByIP(ip,request);
        return jsonResult;
    }

    private JSONObject getIP() {
        String url = "http://pv.sohu.com/cityjson?ie=utf-8";
        Map<String, String> map = new HashMap<>();
        String data = OkHttpUtil.get(url, map);
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(data.substring(19, data.length() - 1));
        return jsonObject;
    }

    private JsonResult getWeatherByIP(String ip,HttpServletRequest request) {
        JSONArray weather;
        JsonResult jsonResult = new JsonResult();
        City city ;
//        Device device = UserAgentUtils.getDevice(request);
        logger.info("ip: " + ip);
        //ipv4的正则规则
        String regEx = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(ip);
        boolean flag = matcher.matches();
        logger.info("ip格式是否正确：" + flag);
        if (flag) {
            city = okHttpService.getCityByIp(ip);
            if (city != null) {
//                device.setIp(ip);
//                device.setCity(city.getCityName());
//                deviceService.saveDevice(device);
                String userAgent = UserAgentUtils.getUserAgent(request);
                deviceSender.send(userAgent,ip,city.getCityName());
                weather = okHttpService.getWeatherByCityId(city.getCityId());
                if (weather != null) {
                    jsonResult.setMessage("获取天气成功！");
                    jsonResult.setData(weather);
                } else {
                    jsonResult.setCode(ResultCode.UNKONW_ERROR);
                    jsonResult.setMessage("获取天气失败！");
                }
            } else {
                jsonResult.setCode(ResultCode.UNKONW_ERROR);
                jsonResult.setMessage("ip地址不正确！");
            }
        } else {
            jsonResult.setCode(ResultCode.UNKONW_ERROR);
            jsonResult.setMessage("ip地址格式不正确！");
        }
        return jsonResult;
    }

}
