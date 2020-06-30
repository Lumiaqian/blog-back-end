package com.caoyuqian.blogsvc.service.impl;

import com.caoyuqian.blogapi.dto.CreatePostTagRequest;
import com.caoyuqian.blogsvc.entity.PostTag;
import com.caoyuqian.blogsvc.mapper.PostTagMapper;
import com.caoyuqian.blogsvc.service.PostTagService;
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
 * @Title: PostTagServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: PostTagServiceImpl
 * @date 2020/6/30 4:20 下午
 **/
@Slf4j
@Service
public class PostTagServiceImpl implements PostTagService {

    @Autowired
    private PostTagMapper postTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(CreatePostTagRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        PostTag postTag = new PostTag();
        BeanUtils.copyProperties(request, postTag);
        return postTagMapper.insert(postTag);
    }
}
