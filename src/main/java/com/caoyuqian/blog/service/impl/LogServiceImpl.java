package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.LogMapper;
import com.caoyuqian.blog.pojo.Log;
import com.caoyuqian.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: LogServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2019-04-15 11:08
 **/
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int save(Log log) {
        return logMapper.save(log);
    }
}
