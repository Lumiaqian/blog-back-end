package com.caoyuqian.blogsvc.converter;

import com.caoyuqian.blogapi.dto.PostDto;
import com.caoyuqian.blogsvc.entity.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author qian
 * @version V1.0
 * @Title: Post2PostVoConverter
 * @Package: com.caoyuqian.blogsvc.converter
 * @Description: TOTO
 * @date 2020/6/29 4:17 下午
 **/
@Component
public class Post2PostDtoConverter implements Converter<Post, PostDto> {
    @Override
    public PostDto convert(Post post) {
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        return postDto;
    }
}
