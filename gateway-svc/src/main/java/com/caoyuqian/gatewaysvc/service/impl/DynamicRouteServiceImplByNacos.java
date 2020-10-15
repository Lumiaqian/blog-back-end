package com.caoyuqian.gatewaysvc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.caoyuqian.gatewaysvc.config.GatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author qian
 * @version V1.0
 * @Title: DynamicRouteServiceImplByNacos
 * @Package: com.caoyuqian.gatewaysvc.service
 * @Description: 通过Nacos触发动态路由
 * @date 2020/9/3 12:20 下午
 **/
@Component
@Slf4j
@DependsOn({"gatewayConfig"})
public class DynamicRouteServiceImplByNacos {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    private ConfigService configService;

    @PostConstruct
    public void init() {
        log.info("gateway route init...");
        try{
            configService = initConfigService();
            if(configService == null){
                log.warn("initConfigService fail");
                return;
            }
            log.info("data id:{},group:{},default timeout:{}",GatewayConfig.NACOS_ROUTE_DATA_ID,GatewayConfig.NACOS_ROUTE_GROUP,GatewayConfig.DEFAULT_TIMEOUT);
            String configInfo = configService.getConfig(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP, GatewayConfig.DEFAULT_TIMEOUT);
            log.info("获取网关当前配置:\r\n{}",configInfo);
            List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
            for(RouteDefinition definition : definitionList){
                log.info("update route : {}",definition.toString());
                dynamicRouteService.add(definition);
            }
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误",e);
        }
        dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID,GatewayConfig.NACOS_ROUTE_GROUP);
    }

    /**
     * 监听Nacos下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}",configInfo);
                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    for(RouteDefinition definition : definitionList){
                        log.info("update route : {}",definition.toString());
                        dynamicRouteService.update(definition);
                    }
                }
                @Override
                public Executor getExecutor() {
                    log.info("getExecutor\n\r");
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("从nacos接收动态路由配置出错!!!",e);
        }
    }
    /**
     * @param
     * @return com.alibaba.nacos.api.config.ConfigService
     * @Description: 初始化网关路由
     * @version 0.1.0
     * @author qian
     * @date 2020/9/3 12:37 下午
     * @since 0.1.0
     */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayConfig.NACOS_SERVER_ADDR);
            // properties.setProperty("namespace",GatewayConfig.NACOS_NAMESPACE);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误", e);
            return null;
        }
    }
}
