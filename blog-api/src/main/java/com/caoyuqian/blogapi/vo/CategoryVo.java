package com.caoyuqian.blogapi.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: CategoryVo
 * @date 2020/7/2 2:00 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryVo {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 上级分类id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    private LocalDateTime createTime;
}
