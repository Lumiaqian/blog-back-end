package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

import java.util.List;
import java.util.stream.Collectors;

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
public class PostCategoryServiceImpl extends ServiceImpl<PostCategoryMapper,PostCategory> implements PostCategoryService {

    @Autowired
    private PostCategoryMapper postCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveList(List<CreatePostCateRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
       List<PostCategory> postCategories =
               requests.stream().map(request -> {
                   PostCategory postCategory = new PostCategory();
                   BeanUtils.copyProperties(request,postCategory);
                   return postCategory;
               }).collect(Collectors.toList());
        return this.saveOrUpdateBatch(postCategories);
    }

}
