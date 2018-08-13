package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.CityMapper;
import com.caoyuqian.blog.pojo.City;
import com.caoyuqian.blog.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author qian
 * @version V1.0
 * @Title: CityServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: CityService
 * @date 2018/8/12 下午2:41
 **/
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Cacheable(value = "city",key = "#cityName")
    public City getCityByName(String cityName) {
        return cityMapper.getCityByName(cityName);
    }
}
