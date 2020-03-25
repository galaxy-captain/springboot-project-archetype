package me.galaxy.archetype.caller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:17 上午
 **/
@EnableScheduling
@Configuration
public class ScheduleConfiguration {

    @Bean
    public ThreadPoolTaskScheduler scheduledThreadPoolExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("thread-scheduler-");
        scheduler.setPoolSize(32);
        return scheduler;
    }

}