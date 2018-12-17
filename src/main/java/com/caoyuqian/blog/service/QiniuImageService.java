package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.QiniuImage;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QiniuImageService {
    int save( QiniuImage qiniuImage);
    PageInfo<QiniuImage> getQinius(int pageNo,int pageSize);
}
