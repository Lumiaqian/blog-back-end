package com.caoyuqian.blogsvc.converter;

import com.caoyuqian.blogapi.vo.PostVo;
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
public class Post2PostVoConverter implements Converter<Post, PostVo> {
    @Override
    public PostVo convert(Post post) {
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(post,postVo);
        return postVo;
    }
}
