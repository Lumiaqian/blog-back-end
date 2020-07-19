package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.blogapi.dto.CreateTagRequest;
import com.caoyuqian.blogapi.dto.UpdateTagRequest;
import com.caoyuqian.blogapi.vo.PostTagVo;
import com.caoyuqian.blogapi.vo.TagVo;
import com.caoyuqian.blogsvc.entity.Tag;
import com.caoyuqian.blogsvc.mapper.TagMapper;
import com.caoyuqian.blogsvc.service.PostTagService;
import com.caoyuqian.blogsvc.service.TagService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: TagServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: TagServiceImpl
 * @date 2020/6/30 4:12 下午
 **/
@Slf4j
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private PostTagService postTagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TagVo saveOrUpdate(CreateTagRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Tag tag = new Tag();
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(request, tag);
        //根据标签名称，存在更新，不存在插入
        if (checkByName(request.getTagName())) {
            UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("tag_name", request.getTagName());
            tagMapper.update(tag, updateWrapper);
            tagVo = getByName(request.getTagName());
        } else {
            tagMapper.insert(tag);
            BeanUtils.copyProperties(tag, tagVo);
        }
        return tagVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TagVo> saveOrUpdateList(List<CreateTagRequest> requests) {

        return requests.stream().map(this::saveOrUpdate).collect(Collectors.toList());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TagVo getByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_name", name);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Tag tag = tagMapper.selectOne(queryWrapper);
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateList(List<UpdateTagRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        List<Tag> tags = requests.stream().map(updateTagRequest -> {
            Tag tag = new Tag();
            BeanUtils.copyProperties(updateTagRequest, tag);
            return tag;
        }).collect(Collectors.toList());
        return this.updateBatchById(tags);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TagVo> getByPostId(Long postId) {
        if (postId == null || postId == 0) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        List<PostTagVo> postTagVoList = postTagService.getByPostId(postId);
        if (postTagVoList == null || postTagVoList.isEmpty()){
            return null;
        }
        List<Tag> tags = postTagVoList.stream()
                .map(postTagVo -> tagMapper.selectById(postTagVo.getTagId()))
                .collect(Collectors.toList());
        return tags.stream().map(tag -> {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            return tagVo;
        }).collect(Collectors.toList());
    }

    /**
     * @param name
     * @return boolean
     * @Description: 判断标签名称是否存在 true-->存在
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 4:21 下午
     * @since 0.1.0
     */
    private boolean checkByName(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_name", name);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Tag tag = tagMapper.selectOne(queryWrapper);
        return tag != null;
    }
}
