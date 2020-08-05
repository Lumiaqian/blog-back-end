package com.caoyuqian.blogsvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuqian.blogapi.dto.*;
import com.caoyuqian.blogapi.vo.*;
import com.caoyuqian.blogapi.dto.PostDto;
import com.caoyuqian.blogsvc.converter.CreatePostRequest2PostConverter;
import com.caoyuqian.blogsvc.converter.Post2PostDtoConverter;
import com.caoyuqian.blogsvc.entity.Post;
import com.caoyuqian.blogsvc.mapper.PostMapper;
import com.caoyuqian.blogsvc.service.*;
import com.caoyuqian.common.api.Status;
import com.caoyuqian.common.error.ServiceException;
import com.caoyuqian.common.utils.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    private Post2PostDtoConverter post2PostDtoConverter;

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
    public PostDto saveOrUpdate(CreatePostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Post post = createPostRequest2PostConverter.convert(request);
        PostDto postDto = new PostDto();
        //判断文章标题是否存在；存在更新，不存在插入
        if (checkByName(request.getTitle())) {
            UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("title", request.getTitle());
            postMapper.update(post, updateWrapper);
            postDto = getByName(request.getTitle());
        } else {
            postMapper.insert(post);
            postDto = post2PostDtoConverter.convert(post);
        }
        return postDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<PostVo> getAllPub(PostQuery postQuery) {
        IPage<Post> iPage = postMapper.selectPage(postQuery.getPage(), null);
        return iPage.convert(post -> {
            //获取tags
            List<TagVo> tagVoList = tagService.getByPostId(post.getPostId());
            //获取categories
            List<CategoryVo> categoryVoList = categoryService.getByPostId(post.getPostId());
            List<String> tags = new ArrayList<>();
            List<String> categories = new ArrayList<>();
            if (categoryVoList != null && !categoryVoList.isEmpty()
                    && tagVoList != null && !tagVoList.isEmpty()) {
                tags = tagVoList.stream().map(TagVo::getTagName).collect(Collectors.toList());
                categories = categoryVoList.stream().map(CategoryVo::getCategoryName).collect(Collectors.toList());
            }
            return PostVo.builder()
                    .postId(post.getPostId())
                    .content(post.getContent())
                    .title(post.getTitle())
                    .status(post.getStatus())
                    .isOpenComment(post.isOpenComment())
                    .createTime(post.getCreateTime())
                    .publicTime(post.getPublicTime())
                    .updateTime(post.getUpdateTime())
                    .categories(categories)
                    .tags(tags)
                    .build();
        });
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

        //生成tag
        Set<CreateTagRequest> tags = new LinkedHashSet<>();
        if (postYamlDTO.getTags() != null && !postYamlDTO.getTags().isEmpty()) {

            tags = postYamlDTO.getTags().stream()
                    .map(s -> CreateTagRequest.builder()
                            .tagName(s).build())
                    .collect(Collectors.toSet());
        }
        Set<CreateCateRequest> categories = new LinkedHashSet<>();
        //生成Category
        if (postYamlDTO.getCategories() != null && !postYamlDTO.getCategories().isEmpty()) {


            CreateCateRequest categoryFirst = CreateCateRequest.builder()
                    .categoryName(postYamlDTO.getCategories().get(0))
                    .build();
            CategoryVo categoryVoFirst = categoryService.saveOrUpdate(categoryFirst);
            for (int i = 0; i < postYamlDTO.getCategories().size(); i++) {
                CreateCateRequest category = CreateCateRequest.builder()
                        .categoryName(postYamlDTO.getCategories().get(i))
                        .build();
                if (i != 0) {
                    category.setParentId(categoryVoFirst.getCategoryId());
                }
                categories.add(category);
            }

        }
        // 存入数据库
        SavePostRequest savePostRequest = SavePostRequest.builder()
                .post(post)
                .categories(new ArrayList<>(categories))
                .tags(new ArrayList<>(tags))
                .build();
        getService().savePost(savePostRequest);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePost(SavePostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        CreatePostRequest post = request.getPost();
        //保存文章
        PostDto postDto = getService().saveOrUpdate(post);
        //保存标签
        List<TagVo> tagVos = tagService.saveOrUpdateList(request.getTags());
        //保存标签与文章的关系
        List<CreatePostTagRequest> createPostTagRequests = new ArrayList<>();
        log.info("PostId:{}", postDto.getPostId());
        if (tagVos != null && !tagVos.isEmpty()) {
            createPostTagRequests = tagVos.stream().map(tagVo -> CreatePostTagRequest.builder()
                    .postId(postDto.getPostId())
                    .tagId(tagVo.getTagId())
                    .build()).collect(Collectors.toList());
            postTagService.saveList(createPostTagRequests);
        }
        //保存分类
        List<CategoryVo> categoryVos = categoryService.saveOrUpdateList(request.getCategories());
        //保存post与category的关联
        List<CreatePostCateRequest> createPostCateRequests = new ArrayList<>();
        if (categoryVos != null && !categoryVos.isEmpty()) {
            createPostCateRequests = categoryVos.stream().map(categoryVo -> CreatePostCateRequest.builder()
                    .categoryId(categoryVo.getCategoryId())
                    .postId(postDto.getPostId())
                    .build()).collect(Collectors.toList());
            postCategoryService.saveList(createPostCateRequests);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPost(EditPostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }

        UpdatePostRequest post = request.getPost();
        List<CreateCateRequest> categories = new ArrayList<>();
        List<CreateTagRequest> tags = new ArrayList<>();
        BeanUtils.copyProperties(request.getCategories(), categories);
        BeanUtils.copyProperties(request.getTags(), tags);
        // 更新post
        PostDto postDto = getService().update(post);
        //保存标签
        List<TagVo> tagVos = tagService.saveOrUpdateList(tags);
        //保存标签与文章的关系
        List<CreatePostTagRequest> createPostTagRequests = new ArrayList<>();
        log.info("PostId:{}", postDto.getPostId());
        if (tagVos != null && !tagVos.isEmpty()) {
            createPostTagRequests = tagVos.stream().map(tagVo -> CreatePostTagRequest.builder()
                    .postId(postDto.getPostId())
                    .tagId(tagVo.getTagId())
                    .build()).collect(Collectors.toList());
            postTagService.saveList(createPostTagRequests);
        }
        //保存分类
        List<CategoryVo> categoryVos = categoryService.saveOrUpdateList(categories);
        //保存post与category的关联
        List<CreatePostCateRequest> createPostCateRequests = new ArrayList<>();
        if (categoryVos != null && !categoryVos.isEmpty()) {
            createPostCateRequests = categoryVos.stream().map(categoryVo -> CreatePostCateRequest.builder()
                    .categoryId(categoryVo.getCategoryId())
                    .postId(postDto.getPostId())
                    .build()).collect(Collectors.toList());
            postCategoryService.saveList(createPostCateRequests);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostDto update(UpdatePostRequest request) {
        if (request == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        Post post = new Post();
        BeanUtils.copyProperties(entityClass, post);

        postMapper.updateById(post);

        return post2PostDtoConverter.convert(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<ManagementPostVo> getPostList(PostQuery postQuery) {
        if (postQuery == null) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        IPage<Post> iPage = postQuery.getPage();
        //获取分页内容
        List<Post> posts = postMapper.getPostList(iPage, postQuery);
        //设置分页内容
        iPage.setRecords(posts);
        return iPage.convert(post -> {
            //获取tags
            List<TagVo> tagVoList = tagService.getByPostId(post.getPostId());
            //获取categories
            List<CategoryVo> categoryVoList = categoryService.getByPostId(post.getPostId());
            List<TagMenuVo> tags = new ArrayList<>();
            List<CategoryMenuVo> categories = new ArrayList<>();
            if (categoryVoList != null && !categoryVoList.isEmpty()
                    && tagVoList != null && !tagVoList.isEmpty()) {
                tags = tagVoList.stream().map(tagVo -> TagMenuVo.builder()
                        .tagId(tagVo.getTagId())
                        .tagName(tagVo.getTagName())
                        .build()).collect(Collectors.toList());
                categories = categoryVoList.stream().map(categoryVo -> CategoryMenuVo.builder()
                        .categoryId(categoryVo.getCategoryId())
                        .parentId(categoryVo.getParentId())
                        .categoryName(categoryVo.getCategoryName())
                        .build()).collect(Collectors.toList());
            }
            return ManagementPostVo.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .status(post.getStatus())
                    .isOpenComment(post.isOpenComment())
                    .createTime(post.getCreateTime())
                    .publicTime(post.getPublicTime())
                    .updateTime(post.getUpdateTime())
                    .categories(categories)
                    .tags(tags)
                    .build();
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PostVo getPubPostById(Long postId) {
        if (postId == null || postId == 0) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId)
                .eq("status", com.caoyuqian.common.constant.Status.PUBLIC);
        Post post = postMapper.selectOne(queryWrapper);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(post, postVo);
        //获取tags
        List<TagVo> tagVoList = tagService.getByPostId(post.getPostId());
        //获取categories
        List<CategoryVo> categoryVoList = categoryService.getByPostId(post.getPostId());
        List<String> tags = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        if (categoryVoList != null && !categoryVoList.isEmpty()
                && tagVoList != null && !tagVoList.isEmpty()) {
            tags = tagVoList.stream().map(TagVo::getTagName).collect(Collectors.toList());
            categories = categoryVoList.stream().map(CategoryVo::getCategoryName).collect(Collectors.toList());
        }
        postVo.setCategories(categories);
        postVo.setTags(tags);
        return postVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManagementPostVo getManagementPostById(Long postId, Integer status) {
        if (postId == null || postId == 0 || status == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",postId).eq("status",status);
        Post post = postMapper.selectOne(queryWrapper);
        ManagementPostVo managementPostVo = new ManagementPostVo();
        BeanUtils.copyProperties(post,managementPostVo);
        //获取tags
        List<TagVo> tagVoList = tagService.getByPostId(post.getPostId());
        //获取categories
        List<CategoryVo> categoryVoList = categoryService.getByPostId(post.getPostId());
        List<TagMenuVo> tags = new ArrayList<>();
        List<CategoryMenuVo> categories = new ArrayList<>();
        if (categoryVoList != null && !categoryVoList.isEmpty()
                && tagVoList != null && !tagVoList.isEmpty()) {
            tags = tagVoList.stream().map(tagVo -> TagMenuVo.builder()
                    .tagId(tagVo.getTagId())
                    .tagName(tagVo.getTagName())
                    .build()).collect(Collectors.toList());
            categories = categoryVoList.stream().map(categoryVo -> CategoryMenuVo.builder()
                    .categoryId(categoryVo.getCategoryId())
                    .parentId(categoryVo.getParentId())
                    .categoryName(categoryVo.getCategoryName())
                    .build()).collect(Collectors.toList());
        }
        managementPostVo.setCategories(categories);
        managementPostVo.setTags(tags);
        return managementPostVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(UpdatePostStatusRequest status) {
        if (status == null){
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Post::getStatus,status.getStatus())
                .eq(Post::getPostId,status.getPostId());
        baseMapper.update(null,updateWrapper);

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
        if (StringUtils.isBlank(postName)) {
            return false;
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", postName);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Post post = postMapper.selectOne(queryWrapper);
        return post != null;
    }

    /**
     * 解决事务失效
     *
     * @return PostServiceImpl
     */
    private PostServiceImpl getService() {
        return SpringUtil.getBean(this.getClass());
    }

    private PostDto getByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ServiceException(Status.PARAM_IS_NULL);
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.ne("status", com.caoyuqian.common.constant.Status.DELETE);
        Post post = postMapper.selectOne(queryWrapper);
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(post, postDto);
        return postDto;
    }
}
