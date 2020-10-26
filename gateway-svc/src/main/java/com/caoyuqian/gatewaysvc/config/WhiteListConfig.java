package com.caoyuqian.gatewaysvc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: WhiteListConfig
 * @Package: com.caoyuqian.gatewaysvc.config
 * @Description: 白名单配置
 * @date 2020/10/26 4:18 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "whitelist")
public class WhiteListConfig {

    private List<String> urls;
}
