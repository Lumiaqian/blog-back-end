package com.caoyuqian.blog.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author qian
 * @version V1.0
 * @Title: PageHelperConfig
 * @Package: com.caoyuqian.blog.config
 * @Description: 分页插件配置类
 * @date 2018/8/16 下午7:40
 **/
@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "PostgreSQL");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
