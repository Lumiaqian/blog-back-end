package com.caoyuqian.blogsvc.service.impl;

import com.caoyuqian.blogapi.dto.CreatePostCateRequest;
import com.caoyuqian.blogsvc.entity.PostCategory;
import com.caoyuqian.blogsvc.mapper.PostCategoryMapper;
import com.caoyuqian.blogsvc.service.PostCategoryService;
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
 * @Title: PostCategoryServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: PostCategoryServiceImpl
 * @date 2020/6/30 4:18 下午
 **/
@Slf4j
@Service
public class PostCategoryServiceImpl implements PostCategoryService {

    @Autowired
    private PostCategoryMapper postCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(CreatePostCateRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        PostCategory postCategory = new PostCategory();
        BeanUtils.copyProperties(request, postCategory);
        return postCategoryMapper.insert(postCategory);
    }
}
