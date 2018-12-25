package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.repository.PostRepository;
import com.caoyuqian.blog.service.PostRepositoryService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.Highlighter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author qian
 * @version V1.0
 * @Title: PostRepositoryServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018/9/12 下午7:36
 **/
@Service
public class PostRepositoryServiceImpl implements PostRepositoryService {

    private final Logger logger= LoggerFactory.getLogger(PostRepositoryServiceImpl.class);

    @Autowired
    private PostRepository postRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void saveAll(List<Post> posts) {
        if (!posts.isEmpty()&&posts!=null){
            postRepository.saveAll(posts);
        }else {
            logger.error("posts为空！");
        }
    }

    @Override
    public void save(Post post) {
        if (post!=null){
            postRepository.save(post);
        }else {
            logger.error("post为空！");
        }
    }

    @Override
    public Page<Post> getListByKey(Integer pageNum, Integer pageSize, String searchContent) {
        if (pageNum==null) {
            pageNum = 0;
        }
        // 分页参数,从第0页开始
        logger.info("pageNum: "+pageNum+"pageSize: "+pageSize);
        logger.info("kw: "+searchContent);
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        // 创建QueryBuild
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        // 设置模糊查询
        //boolQueryBuilder.must(QueryBuilders.fuzzyQuery("content",searchContent).fuzziness(Fuzziness.AUTO));
        // boolQueryBuilder.must(new QueryStringQueryBuilder("title").field(searchContent));
        // boolQueryBuilder.must(QueryBuilders.matchQuery("content",searchContent));
        // boolQueryBuilder.must(QueryBuilders.termQuery("content",searchContent));
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchContent,"content","title"));
        // boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        // 构建查询
//        NativeSearchQueryBuilder nativeSearchQueryBuilder=new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(boolQueryBuilder).withPageable(pageable);
        // 生成search
        SearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withHighlightFields()
                .withQuery(boolQueryBuilder)
                .build();
        
        Page<Post> posts=postRepository.search(searchQuery);
        return posts;
    }

    private SearchQuery getPostSearchQuery(int pageNum,int pageSize,String searchContent){
       /* FunctionScoreQueryBuilder functionScoreQueryBuilder= QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("title",searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100))
                .add(QueryBuilders.matchPhraseQuery("content",searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100))
                //设置权重分，求和模式
                .scoreMode("sum")
                //设置权重分最低分
                .setMinScore(10);*/
        // 分页参数
        Pageable pageable = new PageRequest(pageNum, pageSize);


        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(fuzzyQuery("content",searchContent))
                .build();
        return  searchQuery;
    }
}
