package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.QiniuImageMapoper;
import com.caoyuqian.blog.pojo.QiniuImage;
import com.caoyuqian.blog.service.QiniuImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QiniuImageServiceImpl implements QiniuImageService {
    @Autowired
    private QiniuImageMapoper qiniuImageMapoper;

    @Override
    public int save(QiniuImage qiniuImage) {
        return qiniuImageMapoper.save(qiniuImage);
    }

    @Override
    public PageInfo<QiniuImage> getQinius(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<QiniuImage> list = qiniuImageMapoper.getQinius();
        PageInfo<QiniuImage> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
