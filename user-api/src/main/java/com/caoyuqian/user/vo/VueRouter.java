package com.caoyuqian.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建 Vue路由
 *
 * @author lumiaqian
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VueRouter<T> implements Serializable {


    private static final long serialVersionUID = -4134351197349911372L;

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long parentId;

    private String path;

    private String name;

    private String component;

    private String redirect;

    private RouterMetaVo meta;

    private Boolean hidden = false;

    private Boolean alwaysShow = false;

    private List<VueRouter<T>> children;

    @JsonIgnore
    private Boolean hasParent = false;

    @JsonIgnore
    private Boolean hasChildren = false;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
