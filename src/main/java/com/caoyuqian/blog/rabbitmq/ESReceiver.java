package com.caoyuqian.blog.rabbitmq;

import com.caoyuqian.blog.pojo.Device;
import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.repository.PostRepository;
import com.caoyuqian.blog.utils.UserAgentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author qian
 * @version V1.0
 * @Title: ESReceiver
 * @Package: com.caoyuqian.blog.rabbitmq
 * @Description: ES消费者
 * @date 2019-03-01 15:16
 **/
@Component
@RabbitListener(queues = "es")
public class ESReceiver {

    @Autowired
    private PostRepository postRepository;

    private final Logger logger = LoggerFactory.getLogger(ESReceiver.class);

    @RabbitHandler
    public void process(Post post) throws Exception {
        // logger.info("rabbitMQ receiver: "+city);
        logger.info("rabbitMQ receiver: " + post.toString());
        postRepository.save(post);
    }

}
