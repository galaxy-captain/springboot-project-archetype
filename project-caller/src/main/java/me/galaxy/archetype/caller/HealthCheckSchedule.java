package me.galaxy.archetype.caller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:15 上午
 **/
@Slf4j
@Service
public class HealthCheckSchedule {

    @Scheduled(cron = "0/10 * *  * * ?")
    public void checkOuterServer() {
        log.info("outer server is alive.");
    }

}