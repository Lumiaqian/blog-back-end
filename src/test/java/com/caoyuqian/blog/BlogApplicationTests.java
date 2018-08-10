package com.caoyuqian.blog;

import com.caoyuqian.blog.utils.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
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
}
