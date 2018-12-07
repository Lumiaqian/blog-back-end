package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.SettingMapper;
import com.caoyuqian.blog.pojo.Setting;
import com.caoyuqian.blog.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: SettingServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018-12-07 16:38
 **/
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingMapper settingMapper;

    @Override
    public int saveSetting(Setting setting) {
        return settingMapper.saveSetting(setting);
    }
}
