package com.caoyuqian.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Vue路由 Meta
 *
 * @author lumiaqian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {


    private static final long serialVersionUID = 554885993972852685L;

    private String title;

    private String icon;

    @JsonSerialize(using = ToStringSerializer.class)
    private List<Long> roles;

}
