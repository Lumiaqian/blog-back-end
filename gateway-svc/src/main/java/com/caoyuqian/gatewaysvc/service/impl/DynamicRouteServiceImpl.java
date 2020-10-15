package com.caoyuqian.gatewaysvc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author qian
 * @version V1.0
 * @Title: DynamicRouteServiceImpl
 * @Package: com.caoyuqian.gatewaysvc.service.impl
 * @Description: 动态路更新路由网关service
 * 1）实现一个Spring提供的事件推送接口ApplicationEventPublisherAware
 * 2）提供动态路由的基础方法，可通过获取bean操作该类的方法。该类提供新增路由、更新路由、删除路由，然后实现发布的功能。
 * @date 2020/9/3 12:23 下午
 **/
@Service
@Slf4j
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * @param id
     * @return java.lang.String
     * @Description: 删除路由
     * @version 0.1.0
     * @author qian
     * @date 2020/9/3 12:33 下午
     * @since 0.1.0
     */
    public String delete(String id) {
        try {
            log.info("gateway delete route id {}", id);
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            return "delete fail";
        }
    }

    /**
     * @param definition
     * @return java.lang.String
     * @Description: 更新路由
     * @version 0.1.0
     * @author qian
     * @date 2020/9/3 12:32 下午
     * @since 0.1.0
     */
    public String update(RouteDefinition definition) {
        try {
            log.info("gateway update route {}", definition);
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route fail";
        }
    }


    /**
     * @param definition
     * @return java.lang.String
     * @Description: 增加路由
     * @version 0.1.0
     * @author qian
     * @date 2020/9/3 12:32 下午
     * @since 0.1.0
     */
    public String add(RouteDefinition definition) {
        log.info("gateway add route {}", definition);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }
}
