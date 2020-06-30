package com.caoyuqian.blogsvc.service.impl;

import com.caoyuqian.blogapi.dto.CreateCateRequest;
import com.caoyuqian.blogsvc.entity.Category;
import com.caoyuqian.blogsvc.mapper.CategoryMapper;
import com.caoyuqian.blogsvc.service.CategoryService;
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
 * @Title: CategoryServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: CategoryServiceImpl
 * @date 2020/6/30 4:15 下午
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(CreateCateRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Category category = new Category();
        BeanUtils.copyProperties(request,category);
        return categoryMapper.insert(category);
    }
}
