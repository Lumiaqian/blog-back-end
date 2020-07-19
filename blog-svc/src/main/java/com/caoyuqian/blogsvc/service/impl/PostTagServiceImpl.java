package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.blogapi.dto.CreatePostTagRequest;
import com.caoyuqian.blogapi.vo.PostTagVo;
import com.caoyuqian.blogsvc.entity.PostTag;
import com.caoyuqian.blogsvc.mapper.PostTagMapper;
import com.caoyuqian.blogsvc.service.PostTagService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @version V1.0
 * @Title: PostTagServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: PostTagServiceImpl
 * @date 2020/6/30 4:20 下午
 **/
@Slf4j
@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

    @Autowired
    private PostTagMapper postTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveList(List<CreatePostTagRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Long postId = requests.get(0).getPostId();

        List<PostTag> postTags =
                requests.stream().map(request -> {
                    PostTag postTag = new PostTag();
                    BeanUtils.copyProperties(request, postTag);
                    return postTag;
                }).collect(Collectors.toList());
        // 先删除，再插入
        QueryWrapper<PostTag> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("post_id", postId);
        postTagMapper.delete(deleteWrapper);

        this.saveBatch(postTags);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PostTagVo> getByPostId(Long postId) {
        if (postId == null || postId == 0){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<PostTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",postId);
        List<PostTag> postTags = postTagMapper.selectList(queryWrapper);
        return postTags.stream().map(postTag -> {
          PostTagVo postTagVo = new PostTagVo();
          BeanUtils.copyProperties(postTag,postTagVo);
          return postTagVo;
        }).collect(Collectors.toList());
    }
}
