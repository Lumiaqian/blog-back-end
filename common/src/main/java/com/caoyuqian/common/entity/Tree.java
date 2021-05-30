package com.caoyuqian.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: Tree
 * @Package: com.caoyuqian.common.entity
 * @Description: tree
 * @date 2020/7/21 8:50 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Tree<T> {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String label;

    private List<Tree<T>> children;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
