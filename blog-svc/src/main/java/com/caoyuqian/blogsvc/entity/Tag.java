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
 * @Title: Tag
 * @Package: com.caoyuqian.blogsvc.entity
 * @Description: 标签
 * @date 2020/6/30 3:39 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tag")
public class Tag {
    /**
     * 主键
     */
    @TableId(value = "tag_id",type = IdType.ASSIGN_ID)
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 标签的状态1-->正常；0-->删除
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
