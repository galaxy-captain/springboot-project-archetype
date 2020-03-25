package me.galaxy.archetype.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:17 上午
 **/
@EnableScheduling
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {

//    @Autowired
//    @Qualifier("specificScheduledExecutor")
//    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(scheduledThreadPoolExecutor);
    }

//    @Bean(name = "specificScheduledExecutor", destroyMethod = "shutdown")
//    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(8);
//        return scheduledThreadPoolExecutor;
//    }

}