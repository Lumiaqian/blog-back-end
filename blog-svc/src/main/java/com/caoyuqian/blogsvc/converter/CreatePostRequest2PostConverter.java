package com.caoyuqian.blogsvc.converter;

import com.caoyuqian.blogapi.dto.CreatePostRequest;
import com.caoyuqian.blogsvc.entity.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: CreatePostRequest2Post
 * @Package: com.caoyuqian.blogsvc.converter
 * @Description: CreatePostRequest2Post
 * @date 2020/6/29 3:05 下午
 **/
@Component
public class CreatePostRequest2PostConverter implements Converter<CreatePostRequest, Post> {
    @Override
    public Post convert(CreatePostRequest request) {
        Post post = new Post();
        BeanUtils.copyProperties(request,post);
        return post;
    }
}
