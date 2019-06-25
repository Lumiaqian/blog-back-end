package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: DeviceMapper
 * @Package: com.caoyuqian.blog.mapper
 * @Description: TOTO
 * @date 2018-12-29 10:52
 **/
@Mapper
public interface DeviceMapper {

    int saveDevice(@Param("device")Device device);
    List<Device> getDevices();
    int isExistDevice(@Param("device")Device device);
    List<HashMap<String, Object>> getCityCount();
    List<HashMap<String, Object>> getOSCount();
}
