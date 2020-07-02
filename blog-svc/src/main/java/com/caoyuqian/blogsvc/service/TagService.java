package com.caoyuqian.blogsvc.service;

import com.caoyuqian.blogapi.dto.CreateTagRequest;
import com.caoyuqian.blogapi.vo.TagVo;
import com.caoyuqian.blogsvc.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author lumiaqian
 */
public interface TagService {

    /**
     * @param request
     * @return java.lang.Integer
     * @Description: 添加新的标签
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 2:17 下午
     * @since 0.1.0
     */
    TagVo add(CreateTagRequest request);

    /**
     * @param requests
     * @return java.util.List<com.caoyuqian.blogapi.dto.CreateTagRequest>
     * @Description: 批量保存
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 11:13 下午
     * @since 0.1.0
     */
    List<TagVo> saveList(List<CreateTagRequest> requests);

    /**
     * @Description: 根据名称获取
     * @param name
     * @version 0.1.0
     * @return com.caoyuqian.blogapi.vo.TagVo
     * @author qian
     * @date 2020/7/2 4:33 下午
     * @since 0.1.0
     */
    TagVo getByName(String name);
}
