package com.caoyuqian.user.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class RouterMetaVo implements Serializable {


    private static final long serialVersionUID = 554885993972852685L;

    private String title;

    private String icon;

    private Boolean breadcrumb = true;

}
