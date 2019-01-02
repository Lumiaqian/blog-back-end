package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.DeviceMapper;
import com.caoyuqian.blog.pojo.Device;
import com.caoyuqian.blog.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: DeviceServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018-12-29 11:08
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public int saveDevice(Device device) {
        if (deviceMapper.isExistDevice(device)<1){
            deviceMapper.saveDevice(device);
        }
        return 0;
    }

    @Override
    public List<Device> getDevices() {
        return deviceMapper.getDevices();
    }
}
