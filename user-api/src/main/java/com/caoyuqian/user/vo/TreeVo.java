package com.caoyuqian.user.vo;

import com.caoyuqian.common.entity.Tree;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: MenuTreeVo
 * @Package: com.caoyuqian.user.vo
 * @Description: TreeVo
 * @date 2020/7/22 7:27 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeVo {

    private List<? extends Tree<?>> trees;

    private Integer total;
}
