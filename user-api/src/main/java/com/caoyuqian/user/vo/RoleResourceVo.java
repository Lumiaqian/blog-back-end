package com.caoyuqian.user.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qian
 * @version V1.0
 * @Title: RoleResourceVo
 * @Package: com.caoyuqian.user.vo
 * @Description: TOTO
 * @date 2020/10/21 2:24 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResourceVo {


    /**
     * 资源id
     */
    private Long resourceId;


    /**
     * 角色id
     */
    private Long roleId;
}
