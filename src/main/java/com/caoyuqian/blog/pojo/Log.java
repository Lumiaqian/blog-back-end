package com.caoyuqian.blog.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Log
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2019-04-15 10:55
 **/
@Getter
@Setter
@Data
public class Log implements Serializable {
    private long logId;
    private String userId;
    private String operation;
    private String ip;
    private Date createdDate;

}
