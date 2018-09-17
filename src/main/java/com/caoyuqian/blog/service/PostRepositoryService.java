package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostRepositoryService {

    void saveAll(List<Post> posts);

    void save(Post post);

    Page<Post> getListByKey(Integer pageNum, Integer pageSize, String searchContent);
}
