package com.caoyuqian.blog.task;

import com.caoyuqian.blog.pojo.Post;
import com.caoyuqian.blog.service.impl.PostRepositoryServiceImpl;
import com.caoyuqian.blog.service.impl.PostServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: SyncPostDataToES
 * @Package: com.caoyuqian.blog.task
 * @Description: TOTO
 * @date 2019-06-24 21:35
 **/
public class SyncPostDataToES extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(SyncPostDataToES.class);
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private PostRepositoryServiceImpl postRepositoryService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("---开启同步---");
        List<Post> posts = postService.getPost();
        logger.info("PostSize: " + posts.size());
        postRepositoryService.saveAll(posts);
    }
}
