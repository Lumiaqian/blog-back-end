package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Device;

import java.util.List;

public interface DeviceService {
    int saveDevice(Device device);
    List<Device> getDevices();
}
