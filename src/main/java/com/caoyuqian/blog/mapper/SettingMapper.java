package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettingMapper {
    int saveSetting(@Param("setting")Setting setting);
    Setting getSettingBySettingId(@Param("settingId")long id);
    int updateSetting(@Param("setting")Setting setting);
}
