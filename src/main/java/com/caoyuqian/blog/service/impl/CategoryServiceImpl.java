package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.CategoryMapper;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.service.CategoryService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: CategoryServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018/8/15 上午10:54
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int saveCategories(List<Category> categories) {
        return categoryMapper.saveCategories(categories);
    }

    @Override
    public int saveCategory(Category category) {
        return categoryMapper.saveCategory(category);
    }

    @Override
    public int getCountByName(String cateName) {
        return categoryMapper.getCountByName(cateName);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryMapper.getCategoryByName(categoryName);
    }

    @Override
    public int updateCategoryFatherId(Category category) {
        return categoryMapper.updateCategoryFatherId(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }
}
