package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;

public interface LogService {

    int save (Log log);
}
