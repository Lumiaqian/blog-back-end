package com.caoyuqian.blogsvc.service.impl;

import com.caoyuqian.blogapi.dto.CreateTagRequest;
import com.caoyuqian.blogsvc.entity.Tag;
import com.caoyuqian.blogsvc.mapper.TagMapper;
import com.caoyuqian.blogsvc.service.TagService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(CreateTagRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(request, tag);
        return tagMapper.insert(tag);
    }
}
