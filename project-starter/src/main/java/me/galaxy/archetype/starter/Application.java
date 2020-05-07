package me.galaxy.archetype.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 9:32 上午
 **/
@Slf4j
@EnableFeignClients(basePackages = {"me.galaxy.archetype"})
@EnableHystrix
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"me.galaxy.archetype"})
public class Application implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("系统启动完成...");
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setBanner(null);
        application.run(args);
    }

}