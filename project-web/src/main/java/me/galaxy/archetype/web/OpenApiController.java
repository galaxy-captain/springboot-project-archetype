package me.galaxy.archetype.web;

import me.galaxy.archetype.web.config.openapi.OpenAPI;
import me.galaxy.archetype.web.config.openapi.OpenApiRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 6:53 下午
 **/
@RestController
@RequestMapping("/api")
public class OpenApiController {

    @OpenAPI
    @RequestMapping("/test")
    public String test(@RequestBody OpenApiRequest<String> request) {
        return request.getData();
    }

}