package com.caoyuqian.blog;

import com.alibaba.fastjson.JSONObject;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.pojo.City;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import com.caoyuqian.blog.utils.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostServiceImpl postService;
    @Value("${appKey.ak}")
    private String ak;
    @Test
    public void contextLoads() {
        logger.trace("I am trace log.");
        logger.debug("I am debug log.");
        logger.warn("I am warn log.");
        logger.error("I am error log.");
    }

    @Test
    public void testToken(){
        String token;
        token= JwtTokenUtil.CreateToken("lumia",1000*60*60*24);
        logger.info("token "+token);
        logger.info("userId "+JwtTokenUtil.getUserIdFromToken(token));
    }
    @Test
    public void test(){
        String password="123";
        password=new BCryptPasswordEncoder().encode(password);
        logger.info("password: "+password);
    }
    @Test
    public void OKHttp() throws Exception {
        logger.info("ak: "+ak);
        String url="http://api.map.baidu.com/location/ip";
        String ip="244.73.124.65";
        String coor="bd09ll";
        Map<String,String> map=new HashMap();
        map.put("coor",coor);
        map.put("ak",ak);
        map.put("ip",ip);
        /*//logger.info(map.entrySet().);
        Iterator iterator=map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry<String,String>) iterator.next();
            logger.info(entry.getKey().toString());
            logger.info(entry.getValue().toString());
        }*/
        /*String responseBody=OkHttpUtil.get(url,map);
        logger.info("responseBody: "+responseBody);*/
        String str="{\"address\":\"CN|广东|广州|None|CMNET|0|0\",\"content\":{\"address\":\"广东省广州市\",\"address_detail\":{\"city\":\"广州市\",\"city_code\":257,\"district\":\"\",\"province\":\"广东省\",\"street\":\"\",\"street_number\":\"\"},\"point\":{\"x\":\"113.30764968\",\"y\":\"23.12004910\"}},\"status\":0}";
        City city=new City();
        int status=(int) JSONObject.parseObject(str).get("status");
        logger.info("status: "+status);
        JSONObject jsonObject=(JSONObject) JSONObject.parseObject(str).getJSONObject("content").getJSONObject("address_detail");
        logger.info(jsonObject.toString());
        String cityName=(String) jsonObject.get("city");
        city.setCityName(cityName.substring(0,cityName.length()-1));
        //city= JSONUtil.jsonToObj(str,City.class);
        logger.info("city: "+city.toString());
    }
    @Test
    public void dateTest(){
        Post post=new Post();
        post=postService.getPostById("123");
        logger.info(post.toString());
        logger.info(DateUtil.DateToLocalDateTime(post.getPublicDate()).toString());
        logger.info(DateUtil.DateToString(post.getPublicDate()));

    }
    @Test
    public void snowFlake(){
        SnowFlake snowFlake=new SnowFlake(2,3);
        logger.info("snowFlake: "+snowFlake.nextId());
        logger.info("snowFlake: "+snowFlake.nextId());
    }

    @Test
    public void elasticsearchTest(){
        //System.setProperty("es.set.netty.runtime.available.processors", "false");

    }
}
