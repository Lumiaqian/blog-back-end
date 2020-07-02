package com.caoyuqian.blogapi.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qian
 * @version V1.0
 * @Title: TagVo
 * @Package: com.caoyuqian.blogapi.vo
 * @Description: TagVo
 * @date 2020/7/1 11:47 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagVo {

    /**
     * 主键
     */
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
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    private LocalDateTime createTime;
}
