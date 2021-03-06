package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Acate;
import com.caoyuqian.blog.pojo.Category;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {

    int saveCategories(List<Category> categories);
    int saveCategory(Category category);
    int saveCate(Acate acate);
    int getCountByName(String cateName);
    Category getCategoryByName(String categoryName);
    int updateCategoryFatherId(Category category);
    List<Category> getCategories();
    PageInfo<Category> getCategories(int pageNo,int pageSize);
    List<Category> getFatherCates();
    PageInfo<Acate> getCates(int pageNo, int pageSize);
    int getCount();
    Category getCateById(long categoryId);
    int updateCate(Acate acate);
}
