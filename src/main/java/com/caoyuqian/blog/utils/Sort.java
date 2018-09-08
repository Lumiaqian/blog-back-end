package com.caoyuqian.blog.utils;

import com.caoyuqian.blog.pojo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author qian
 * @version V1.0
 * @Title: Sort
 * @Package: com.caoyuqian.blog.utils
 * @Description: 排序工具类
 * @date 2018/9/8 下午11:40
 **/
public class Sort {

    private static final Logger logger= LoggerFactory.getLogger(Sort.class);

     /**
      * @Param:
      * @return:
      * @Author: qian
      * @Description: 对posts按时间排序
      * @Date: 2018/9/9 上午12:29
     **/
    public static void quickSort(List<Post> posts){
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getPublicDate().getTime()>o2.getPublicDate().getTime()) {
                    //logger.info("1");
                    return 1;
                }
                else if (o1.getPublicDate().getTime()<o2.getPublicDate().getTime()){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
    }
}
