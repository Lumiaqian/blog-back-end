package com.caoyuqian.blog.pojo;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: City
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 城市类
 * @date 2018/8/11 下午8:46
 **/
public class City implements Serializable {
    private String cityId;
    private String cityName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
