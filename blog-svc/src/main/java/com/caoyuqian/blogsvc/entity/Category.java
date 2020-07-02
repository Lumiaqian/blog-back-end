package com.caoyuqian.blogsvc.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: Category
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 分类
 * @date 2020/6/30 3:44 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("category")
public class Category {

    /**
     * 主键
     */
    @TableId(value = "category_id",type = IdType.ASSIGN_ID)
    private Long categoryId;

    /**
     * 上级分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类的状态1-->正常；0-->删除
     */
    private Integer status;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
