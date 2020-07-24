package com.caoyuqian.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: VueRouterVo
 * @Package: com.caoyuqian.user.vo
 * @Description: VueRouterVo
 * @date 2020/7/21 10:02 下午
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VueRouterVo implements Serializable {

    private static final long serialVersionUID = -7805950350800838578L;
    private VueRouter<MenuVo> routes;

    private List<String> permissions;
}
