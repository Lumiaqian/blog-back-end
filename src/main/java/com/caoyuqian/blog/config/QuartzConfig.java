package com.caoyuqian.blog.config;

import com.caoyuqian.blog.task.SyncPostDataToES;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qian
 * @version V1.0
 * @Title: QuartzConfig
 * @Package: com.caoyuqian.blog.config
 * @Description: 定时任务配置类
 * @date 2019-06-24 21:40
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail syncESQuartzDetail() {
        return JobBuilder.newJob(SyncPostDataToES.class).withIdentity("sync").storeDurably().build();
    }

    @Bean
    public Trigger syncESQuartzTrigger() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(4)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(syncESQuartzDetail())
                .withIdentity("sync")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
