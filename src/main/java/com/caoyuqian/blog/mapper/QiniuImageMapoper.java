package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.QiniuImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QiniuImageMapoper {
    int save(@Param("qiniu") QiniuImage qiniuImage);
    List<QiniuImage> getQinius();
}
