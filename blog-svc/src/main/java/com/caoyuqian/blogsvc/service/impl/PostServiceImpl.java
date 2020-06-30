package com.caoyuqian.blogsvc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caoyuqian.blogapi.dto.CreatePostRequest;
import com.caoyuqian.blogapi.dto.PostQuery;
import com.caoyuqian.blogapi.vo.PostVo;
import com.caoyuqian.blogsvc.converter.CreatePostRequest2PostConverter;
import com.caoyuqian.blogsvc.converter.Post2PostVoConverter;
import com.caoyuqian.blogsvc.entity.Post;
import com.caoyuqian.blogsvc.mapper.PostMapper;
import com.caoyuqian.blogsvc.service.PostService;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author qian
 * @version V1.0
 * @Title: PostServiceImpl
 * @Package: com.caoyuqian.blogsvc.service.impl
 * @Description: PostServiceImpl
 * @date 2020/6/18 2:43 下午
 **/
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private CreatePostRequest2PostConverter createPostRequest2PostConverter;

    @Autowired
    private Post2PostVoConverter post2PostVoConverter;

    @Autowired
    private PostMapper postMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(CreatePostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        return postMapper.insert(createPostRequest2PostConverter.convert(request));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<PostVo> getAllPub(PostQuery postQuery) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        IPage<Post> iPage = postMapper.selectPage(postQuery.getPage(), queryWrapper);
        return iPage.convert(post -> post2PostVoConverter.convert(post));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadPost(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info(suffixName);
        if (!suffixName.equals(".md")) {
            //文件格式错误
            log.error("文件格式错误！");
            throw new ServiceException(Status.PARAM_ERROR);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String context = FileCopyUtils.copyToString(br);
        //获取markdown中的内容，转换成json格式
        JSONObject object = parseArticle(file.getOriginalFilename(), context);
        Post post = new Post();
        //将获取的JSOn格式转换为post对象
        post = JsonUtil.jsonStr2Obj(object.toJSONString(), Post.class);
    }

    private JSONObject parseArticle(String originalFilename, String context) {
        JSONObject rst = new JSONObject();
        context = StringUtils.trim(context);
        return rst;
    }
}
