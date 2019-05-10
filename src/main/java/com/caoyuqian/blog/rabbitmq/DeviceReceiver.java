package com.caoyuqian.blog.rabbitmq;

import com.caoyuqian.blog.pojo.Device;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.DeviceService;
import com.caoyuqian.blog.utils.UserAgentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * @author qian
 * @version V1.0
 * @Title: DeviceReceiver
 * @Package: com.caoyuqian.blog.rabbitmq
 * @Description: TOTO
 * @date 2019-01-03 15:21
 **/
@Component
@RabbitListener(queues = "device")
public class DeviceReceiver {

    @Autowired
    private DeviceService deviceService;

    private final Logger logger = LoggerFactory.getLogger(DeviceReceiver.class);

    @RabbitHandler
    public void process(HashMap<String, Object> map) throws Exception {
        // logger.info("rabbitMQ receiver: "+city);
        logger.info("rabbitMQ receiver: " + map.toString());
        String userAgent = (String) map.get("userAgent");
        String ip = (String) map.get("ip");
        String city = (String) map.get("city");
        Date createdTime = new Date((Long) map.get("createdTime"));
        Device device = new Device();
        device = UserAgentUtils.getDevice(userAgent);
        if (device!=null){
            device.setIp(ip);
            device.setCity(city);
            device.setCreatedTime(createdTime);
            deviceService.saveDevice(device);
        }
    }
    @RabbitHandler
    public void process(Post post) throws Exception {
        // logger.info("rabbitMQ receiver: "+city);
        logger.info("rabbitMQ receiver: " + post.toString());

    }
}
