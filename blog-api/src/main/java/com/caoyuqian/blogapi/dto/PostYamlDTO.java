package com.caoyuqian.blogapi.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: PostYamlDTO
 * @Package: com.caoyuqian.blogapi.dto
 * @Description: TOTO
 * @date 2020/7/1 4:05 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostYamlDTO implements Serializable {
    /**
     * 标题
     */
    private String title;
    /**
     * 标题
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime date;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 分类
     */
    private List<String> categories;
    /**
     * 内容
     */
    private String context;
}
