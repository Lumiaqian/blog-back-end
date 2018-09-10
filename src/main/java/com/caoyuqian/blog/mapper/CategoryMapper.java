package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int saveCategories(@Param("categories")List<Category> categories);
    int saveCategory(@Param("category") Category category);
    int getCountByName(@Param("category_name") String categoryName);
    Category getCategoryByName(@Param("category_name") String categoryName);
    int updateCategoryFatherId(@Param("category") Category category);
    List<Category> getCategories();
}
