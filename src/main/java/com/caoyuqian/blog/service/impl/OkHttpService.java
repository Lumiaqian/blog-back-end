package com.caoyuqian.blog.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.cache.CacheExpire;
import com.caoyuqian.blog.mapper.CityMapper;
import com.caoyuqian.blog.pojo.City;
import com.caoyuqian.blog.utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: OkHttpService
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: 请求服务
 * @date 2018/8/13 上午12:27
 **/
@Service
public class OkHttpService {

    private static final Logger logger= LoggerFactory.getLogger(OkHttpService.class);

    @Value("${appKey.ak}")
    private String ak;

    @Value("${appKey.coor")
    private String coor;

    @Autowired
    private CityMapper cityMapper;
    /**
     * @Param: ip
     * @return:
     * @Author: qian
     * @Description: 通过ip查询到地理城市，缓存30天
     * @Date: 2018/8/12 下午4:23
     **/
    @Cacheable(value = "city",key = "#ip")
    @CacheExpire(60*60*24*30)
    public City getCityByIp(String ip){
        City city=new City();
        String url="http://api.map.baidu.com/location/ip";
        String cityName="";
        String responseIp="";
        Map<String,String> map=new HashMap(16);
        map.put("coor",coor);
        map.put("ak",ak);
        map.put("ip",ip);
        //发起通过ip查询地址的请求
        responseIp= OkHttpUtil.get(url,map);
        logger.info("responseIp："+responseIp);
        //获得查询成功的状态
        int status=(int) JSONObject.parseObject(responseIp).get("status");
        if (status==0){
            //查询成功
            JSONObject jsonObject=(JSONObject) JSONObject.parseObject(responseIp).getJSONObject("content").getJSONObject("address_detail");
            cityName=((String) jsonObject.get("city"));
            cityName=cityName.substring(0,cityName.length()-1);
            logger.info("cityName: "+cityName);
            city=cityMapper.getCityByName(cityName);
            logger.info(city.toString());
            //city.setCityName(cityName);

        }else {
            city=null;
        }

        return city;
    }

    /**
     * @Param: cityId
     * @return:
     * @Author: qian
     * @Description: 通过cityId获取天气，缓存30分钟
     * @Date: 2018/8/12 下午4:36
     **/
    @Cacheable(value = "weather",key = "#cityId")
    @CacheExpire(60*30)
    public JSONArray getWeatherByCityId(String cityId){
        String response="";
        JSONArray weather;
        String url="http://aider.meizu.com/app/weather/listWeather";
        Map<String,String> map=new HashMap<>(16);
        map.put("cityIds",cityId);
        response=OkHttpUtil.get(url,map);
        logger.info("response: "+response);
        //获得查询成功的状态
        String code=(String) JSONObject.parseObject(response).get("code");
        if (code.equals("200")){
            JSONArray jsonObject=(JSONArray) JSONObject.parseObject(response).getJSONArray("value");
            weather=jsonObject;
        }else {
            weather=null;
        }
        return weather;
    }

    public String getImageUrl(String url, MultipartFile file) throws IOException {
        String reUrl = "";
        String response = OkHttpUtil.put(url,file);
        logger.info("response: "+response);
        //获得查询成功的状态
        String code=(String) JSONObject.parseObject(response).get("code");
        if (code.equals("200")){
            JSONObject jsonObject = JSONObject.parseObject(response);
            reUrl = jsonObject.getString("data");
        }
        return  reUrl;
    }
}
