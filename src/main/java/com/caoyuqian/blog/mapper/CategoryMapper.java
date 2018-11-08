package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Acate;
import com.caoyuqian.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int saveCategories(@Param("categories")List<Category> categories);
    int saveCategory(@Param("category") Category category);
    int saveCate(@Param("cate")Acate acate);
    int getCountByName(@Param("category_name") String categoryName);
    Category getCategoryByName(@Param("category_name") String categoryName);
    int updateCategoryFatherId(@Param("category") Category category);
    List<Category> getCategories();
    List<Acate> getCates();
    List<Category> getFatherCates();
    int getCount();
    Category getCateById(@Param("category_id") long categoryId);
}
