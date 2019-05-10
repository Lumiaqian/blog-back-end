package com.caoyuqian.blog.pojo;

import lombok.*;

/**
 * @author qian
 * @version V1.0
 * @Title: ConstantQiniu
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018-12-07 10:47
 **/
@Getter
@Setter
@Data
public class ConstantQiniu {
    private String accessKey;

    private String secretKey;

    private String bucket;

    private String path;
}
