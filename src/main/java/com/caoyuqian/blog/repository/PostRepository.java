package com.caoyuqian.blog.repository;

import com.caoyuqian.blog.pojo.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostRepository extends ElasticsearchRepository<Post,String> {
    //Page<Post> search(SearchQuery searchQuery);
}
