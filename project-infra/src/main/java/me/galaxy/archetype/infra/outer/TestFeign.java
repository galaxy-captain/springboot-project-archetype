package me.galaxy.archetype.infra.outer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/23 7:07 下午
 **/
@FeignClient(name = "testFeign", url = "http://localhost:8080/lookup")
public interface TestFeign {

    @GetMapping(path = "/test1")
    String test();

}