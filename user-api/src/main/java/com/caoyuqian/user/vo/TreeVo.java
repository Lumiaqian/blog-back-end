package com.caoyuqian.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class TreeVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<TreeVo> children;
}
