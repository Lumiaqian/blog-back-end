package com.caoyuqian.user.vo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qian
 * @version V1.0
 * @Title: ResourceVo
 * @Package: com.caoyuqian.user.vo
 * @Description: TOTO
 * @date 2020/10/21 2:24 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVo {


    /**
     * 主键
     */
    private Long resourceId;


    /**
     * 权限名称
     */
    private String name;


    /**
     * 资源路径
     */
    private String url;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
