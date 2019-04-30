package com.caoyuqian.blog.rabbitmq;

import com.caoyuqian.blog.utils.DateUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author qian
 * @version V1.0
 * @Title: deviceSender
 * @Package: com.caoyuqian.blog.rabbitmq
 * @Description: 生产者
 * @date 2019-01-03 15:16
 **/
@Component
public class DeviceSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String userAgent,String ip,String city){
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("userAgent",userAgent);
        map.put("ip",ip);
        map.put("city",city);
        map.put("createdTime", DateUtil.getNow());
        this.rabbitTemplate.convertAndSend("device",map);
    }
}
