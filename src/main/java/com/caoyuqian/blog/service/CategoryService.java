package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Category;

import java.util.List;

public interface CategoryService {

    int saveCategories(List<Category> categories);
    int saveCategory(Category category);
    int getCountByName(String cateName);
    Category getCategoryByName(String categoryName);
    int updateCategoryFatherId(Category category);
    List<Category> getCategories();
    int getCount();
}
