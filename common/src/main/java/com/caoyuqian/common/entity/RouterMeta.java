package com.caoyuqian.common.entity;

import com.caoyuqian.common.json.LongArray2StringSerialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonSerialize(using = LongArray2StringSerialize.class)
    private List<Long> roles;

}
