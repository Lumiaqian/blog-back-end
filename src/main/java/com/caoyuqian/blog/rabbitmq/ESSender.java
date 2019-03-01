package com.caoyuqian.blog.rabbitmq;

import com.caoyuqian.blog.pojo.Post;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author qian
 * @version V1.0
 * @Title: ESSender
 * @Package: com.caoyuqian.blog.rabbitmq
 * @Description: ES生产者
 * @date 2019-03-01 15:17
 **/
@Component
public class ESSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Post post){
        HashMap<String,Object> map = new HashMap<>();
        this.rabbitTemplate.convertAndSend("es",post);
    }
}
