package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.CategoryMapper;
import com.caoyuqian.blog.pojo.Acate;
import com.caoyuqian.blog.pojo.Category;
import com.caoyuqian.blog.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public int saveCate(Acate acate) {
        return categoryMapper.saveCate(acate);
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

    @Override
    public List<Category> getFatherCates() {
        return categoryMapper.getFatherCates();
    }

    @Override
    public PageInfo<Acate> getCates(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Acate> acates = categoryMapper.getCates();
        PageInfo<Acate> cates=new PageInfo<>(acates);
        return cates;
    }


    @Override
    public int getCount() {
        return categoryMapper.getCount();
    }

    @Override
    public Category getCateById(long categoryId) {
        return categoryMapper.getCateById(categoryId);
    }
}
