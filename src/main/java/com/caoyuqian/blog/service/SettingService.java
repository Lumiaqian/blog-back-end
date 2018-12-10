package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Setting;

public interface SettingService {
    int saveSetting(Setting setting);
    int updateSetting(Setting setting);
}
