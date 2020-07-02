package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.blogapi.dto.*;
import com.caoyuqian.blogapi.vo.CategoryVo;
import com.caoyuqian.blogapi.vo.PostVo;
import com.caoyuqian.blogapi.vo.TagVo;
import com.caoyuqian.blogsvc.converter.CreatePostRequest2PostConverter;
import com.caoyuqian.blogsvc.converter.Post2PostVoConverter;
import com.caoyuqian.blogsvc.entity.Category;
import com.caoyuqian.blogsvc.entity.Post;
import com.caoyuqian.blogsvc.mapper.PostMapper;
import com.caoyuqian.blogsvc.service.*;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.*;
import java.util.stream.Collectors;

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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private CreatePostRequest2PostConverter createPostRequest2PostConverter;

    @Autowired
    private Post2PostVoConverter post2PostVoConverter;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostTagService postTagService;
    @Autowired
    private PostCategoryService postCategoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostVo add(CreatePostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Post post = createPostRequest2PostConverter.convert(request);
        //判断文章标题是否存在；存在更新，不存在插入
        if (checkByName(request.getTitle())){
            UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("title", request.getTitle());
            postMapper.update(post,updateWrapper);
        }else {
            postMapper.insert(post);
        }
        return post2PostVoConverter.convert(post);
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
        if (!".md".equals(suffixName)) {
            //文件格式错误
            log.error("文件格式错误！");
            throw new ServiceException(Status.PARAM_ERROR);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String context = FileCopyUtils.copyToString(br);
        //获取markdown中的内容，转换成PostYamlDTO
        PostYamlDTO postYamlDTO = parseArticle(file.getOriginalFilename(), context);
        //生成post
        CreatePostRequest post = CreatePostRequest.builder().content(postYamlDTO.getContext())
                .publicTime(postYamlDTO.getDate())
                .title(postYamlDTO.getTitle())
                .build();
        //存入数据库
        PostVo postVo = this.add(post);

        if (postYamlDTO.getTags() != null && !postYamlDTO.getTags().isEmpty()) {
            //生成tag
            Set<CreateTagRequest> tags = postYamlDTO.getTags().stream()
                    .map(s -> CreateTagRequest.builder()
                            .tagName(s).build())
                    .collect(Collectors.toSet());
            //存入数据库
            List<TagVo> tagVos = tagService.saveList(new ArrayList<>(tags));
            //存入post与tag的关联
            List<CreatePostTagRequest> createPostTagRequests = new ArrayList<>();
            if (tagVos != null && !tagVos.isEmpty()) {
                createPostTagRequests = tagVos.stream().map(tagVo -> CreatePostTagRequest.builder()
                        .postId(postVo.getPostId())
                        .tagId(tagVo.getTagId())
                        .build()).collect(Collectors.toList());
                postTagService.saveList(createPostTagRequests);
            }
        }

        //生成Category
        if (postYamlDTO.getCategories() != null && !postYamlDTO.getCategories().isEmpty()) {

            Set<CreateCateRequest> categories = new LinkedHashSet<>();

            CreateCateRequest categoryFirst = CreateCateRequest.builder()
                    .categoryName(postYamlDTO.getCategories().get(0))
                    .build();
            CategoryVo categoryVoFirst = categoryService.add(categoryFirst);
            for (int i = 0; i < postYamlDTO.getCategories().size(); i++) {
                CreateCateRequest category = CreateCateRequest.builder()
                        .categoryName(postYamlDTO.getCategories().get(i))
                        .build();
                if (i != 0) {
                    category.setParentId(categoryVoFirst.getCategoryId());
                }
                categories.add(category);
            }
            //存入数据库
            List<CategoryVo> categoryVos = categoryService.saveList(new ArrayList<>(categories));
            //存入post与category的关联
            List<CreatePostCateRequest> createPostCateRequests = new ArrayList<>();
            if (categoryVos != null && !categoryVos.isEmpty()) {
                createPostCateRequests = categoryVos.stream().map(categoryVo -> CreatePostCateRequest.builder()
                        .categoryId(categoryVo.getCategoryId())
                        .postId(postVo.getPostId())
                        .build()).collect(Collectors.toList());
                postCategoryService.saveList(createPostCateRequests);
            }
        }


    }

    private PostYamlDTO parseArticle(String originalFilename, String context) {

        context = StringUtils.trim(context);
        String frontMatter = StringUtils.substringBefore(context, "---");
        if (StringUtils.isBlank(frontMatter)) {
            context = StringUtils.substringAfter(context, "---");
            frontMatter = StringUtils.substringBefore(context, "---");
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new JavaTimeModule());

        PostYamlDTO postYamlDTO = new PostYamlDTO();

        try {

            postYamlDTO = mapper.readValue(frontMatter, PostYamlDTO.class);

        } catch (Exception e) {
            log.error(e.getMessage());
            postYamlDTO = PostYamlDTO.builder().title(originalFilename)
                    .build();
        }
        log.info("postYamlDTO:{}", postYamlDTO);
        postYamlDTO.setContext(context);
        return postYamlDTO;
    }

    /**
     * @param postName
     * @return boolean
     * @Description: 判断文章名称是否存在 true-->存在
     * @version 0.1.0
     * @author qian
     * @date 2020/7/2 3:49 下午
     * @since 0.1.0
     */
    private boolean checkByName(String postName) {
        if (StringUtils.isBlank(postName)){
            return false;
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", postName);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Post post = postMapper.selectOne(queryWrapper);
        return post != null;
    }
}
