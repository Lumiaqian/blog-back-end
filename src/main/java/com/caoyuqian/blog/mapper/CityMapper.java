package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {

    /**
     * @param cityName
     * @return city
     */
    City getCityByName(@Param("city_name") String cityName);
}
