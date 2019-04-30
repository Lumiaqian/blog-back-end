package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Log;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {

    int save(@Param("log") Log log);
}
