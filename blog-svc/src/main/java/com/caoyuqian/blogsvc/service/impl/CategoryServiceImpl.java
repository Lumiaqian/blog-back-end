package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.blogapi.dto.CategoryQuery;
import com.caoyuqian.blogapi.dto.CreateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCateRequest;
import com.caoyuqian.blogapi.dto.UpdateCategoryStatusRequest;
import com.caoyuqian.blogapi.vo.CategoryVo;
import com.caoyuqian.blogapi.vo.PostCateVo;
import com.caoyuqian.blogapi.vo.PostTagVo;
import com.caoyuqian.blogapi.vo.TagVo;
import com.caoyuqian.blogsvc.entity.Category;
import com.caoyuqian.blogsvc.entity.Tag;
import com.caoyuqian.blogsvc.mapper.CategoryMapper;
import com.caoyuqian.blogsvc.service.CategoryService;
import com.caoyuqian.blogsvc.service.PostCategoryService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PostCategoryService postCategoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryVo saveOrUpdate(CreateCateRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Category category = new Category();
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(request, category);
        //根据分类名称，存在更新，不存在插入
        if (checkByName(request.getCategoryName())) {
            UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("category_name", request.getCategoryName());
            categoryMapper.update(category, updateWrapper);
            categoryVo = getByName(request.getCategoryName());
        } else {
            categoryMapper.insert(category);
            BeanUtils.copyProperties(category, categoryVo);
        }

        return categoryVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CategoryVo> saveOrUpdateList(List<CreateCateRequest> requests) {
        return requests.stream().map(this::saveOrUpdate).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryVo getByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", name);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Category category = categoryMapper.selectOne(queryWrapper);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateList(List<UpdateCateRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        List<Category> categories = requests.stream().map(updateCateRequest -> {
            Category category = new Category();
            BeanUtils.copyProperties(updateCateRequest, category);
            return category;
        }).collect(Collectors.toList());
        return this.updateBatchById(categories);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CategoryVo> getByPostId(Long postId) {
        if (postId == null || postId == 0) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        List<PostCateVo> postCateVoList = postCategoryService.getByPostId(postId);
        if (postCateVoList == null || postCateVoList.isEmpty()) {
            return null;
        }
        List<Category> categories = postCateVoList.stream()
                .map(postCateVo -> categoryMapper.selectById(postCateVo.getCategoryId()))
                .collect(Collectors.toList());
        return categories.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<CategoryVo> getListByPage(CategoryQuery query) {
        if (query == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        IPage<Category> iPage = query.getPage();
        //获取分页内容
        List<Category> categories = new ArrayList<>();
        //构造查询条件
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(query.getCategoryId() != null, Category::getCategoryId, query.getCategoryId())
                .eq(query.getParentId() != null, Category::getParentId, query.getParentId())
                .eq(StringUtils.isNoneBlank(query.getCategoryName()), Category::getCategoryName, query.getCategoryName())
                .eq(query.getStatus() != null, Category::getStatus, query.getStatus());
        categories = baseMapper.selectList(queryWrapper);
        //设置分页内容
        iPage.setRecords(categories);
        return iPage.convert(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        });
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategoryStatus(UpdateCategoryStatusRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Category::getStatus, request.getStatus())
                .eq(Category::getCategoryId, request.getCategoryId());
        baseMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryVo getCategoryById(Long categoryId) {
        if (categoryId == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Category category = baseMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CategoryVo> getAllPubCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, com.caoyuqian.common.constant.Status.PUBLIC)
                .orderByDesc(Category::getCreateTime);
        List<Category> categories = baseMapper.selectList(queryWrapper);
        List<CategoryVo> categoryVos = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
          categoryVos = categories.stream().map(category -> {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category,categoryVo);
                return categoryVo;
            }).collect(Collectors.toList());
        }
        return categoryVos;
    }

    /**
     * @param name
     * @return boolean
     * @Description: 判断分类名称是否存在 true-->存在
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 4:17 下午
     * @since 0.1.0
     */
    private boolean checkByName(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", name);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Category category = categoryMapper.selectOne(queryWrapper);
        return category != null;
    }
}
