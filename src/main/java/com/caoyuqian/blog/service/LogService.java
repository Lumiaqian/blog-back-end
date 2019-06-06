package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Log;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface LogService {

    int save (Log log);

    PageInfo<Log> selectAllLog(int pageNum,int pageSize);
}
