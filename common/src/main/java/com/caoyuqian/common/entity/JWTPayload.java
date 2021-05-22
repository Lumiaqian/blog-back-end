package com.caoyuqian.common.entity;

import lombok.Data;

/**
 * @author qian
 * @version V1.0
 * @Title: JWTPayload
 * @Package: com.caoyuqian.common.entity
 * @Description: TOTO
 * @date 2021/5/22 3:10 下午
 **/
@Data
public class JWTPayload {

    private String jti;

    private Long exp;
}
