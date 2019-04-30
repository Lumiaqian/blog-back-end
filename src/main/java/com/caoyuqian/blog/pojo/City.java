package com.caoyuqian.blog.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qian
 * @version V1.0
 * @Title: City
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 城市类
 * @date 2018/8/11 下午8:46
 **/
@Getter
@Setter
@Data
public class City implements Serializable {
    private String cityId;
    private String cityName;

}
