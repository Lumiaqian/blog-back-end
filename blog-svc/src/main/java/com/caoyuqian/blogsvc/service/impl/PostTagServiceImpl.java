package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

import java.util.List;
import java.util.stream.Collectors;

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
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Autowired
    private PostTagMapper postTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveList(List<CreatePostTagRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        List<PostTag> postTags =
                requests.stream().map(request -> {
                    PostTag postTag = new PostTag();
                    BeanUtils.copyProperties(request, postTag);
                    return postTag;
                }).collect(Collectors.toList());
        return this.saveOrUpdateBatch(postTags);
    }
}
