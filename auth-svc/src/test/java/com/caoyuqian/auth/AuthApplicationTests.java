package com.caoyuqian.auth;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AuthApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPwd() {
        log.info(passwordEncoder.encode("mono"));
    }

}
