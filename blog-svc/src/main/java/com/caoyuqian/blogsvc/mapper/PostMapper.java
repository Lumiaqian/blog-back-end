package com.caoyuqian.blogsvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caoyuqian.blogapi.dto.PostQuery;
import com.caoyuqian.blogapi.vo.ManagementPostVo;
import com.caoyuqian.blogsvc.entity.Post;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: PostMapper
 * @Package: com.caoyuqian.blogsvc.mapper
 * @Description: PostMapper
 * @date 2020/6/18 2:41 下午
 **/
public interface PostMapper extends BaseMapper<Post> {

    List<Post> getPostList(IPage<Post> page, PostQuery postQuery);

    void updateStatus(Long postId, Integer status);
}
