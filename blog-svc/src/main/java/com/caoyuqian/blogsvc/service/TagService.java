package com.caoyuqian.blogsvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caoyuqian.blogapi.dto.CreateTagRequest;
import com.caoyuqian.blogapi.dto.TagQuery;
import com.caoyuqian.blogapi.dto.UpdateTagRequest;
import com.caoyuqian.blogapi.dto.UpdateTagStatusRequest;
import com.caoyuqian.blogapi.vo.TagVo;
import com.caoyuqian.blogsvc.entity.Tag;


import java.util.List;


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
    TagVo saveOrUpdate(CreateTagRequest request);

    /**
     * @param requests
     * @return java.util.List<com.caoyuqian.blogapi.dto.CreateTagRequest>
     * @Description: 批量保存
     * @version 0.1.0
     * @author qian
     * @date 2020/7/1 11:13 下午
     * @since 0.1.0
     */
    List<TagVo> saveOrUpdateList(List<CreateTagRequest> requests);

    /**
     * @param name
     * @return com.caoyuqian.blogapi.vo.TagVo
     * @Description: 根据名称获取
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 4:33 下午
     * @since 0.1.0
     */
    TagVo getByName(String name);

    /**
     * @param requests
     * @return boolean
     * @Description: 批量更新
     * @version 0.1.0
     * @author qian
     * @date 2020/7/7 2:36 下午
     * @since 0.1.0
     */
    boolean updateList(List<UpdateTagRequest> requests);

    /**
     * @param postId
     * @return java.util.List<com.caoyuqian.blogapi.vo.TagVo>
     * @Description: 根据文章id获取该文章所属的标签
     * @version 0.1.0
     * @author qian
     * @date 2020/7/8 2:51 下午
     * @since 0.1.0
     */
    List<TagVo> getByPostId(Long postId);

    /**
     * @param query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.caoyuqian.blogapi.vo.TagVo>
     * @Description: 分页查询
     * @version 0.1.0
     * @author qian
     * @date 2020/8/4 9:45 下午
     * @since 0.1.0
     */
    IPage<TagVo> getListByPage(TagQuery query);

    /**
     * @param request
     * @return void
     * @Description: 根据id逻辑删除tag
     * @version 0.1.0
     * @author qian
     * @date 2020/8/5 8:32 下午
     * @since 0.1.0
     */
    void updateTagStatus(UpdateTagStatusRequest request);
}
