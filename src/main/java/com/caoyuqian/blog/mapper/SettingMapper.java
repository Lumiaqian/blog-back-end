package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface SettingMapper {
    int saveSetting(@Param("setting")Setting setting);
    Setting getSettingByUserId(@Param("userId")String userId);
}
