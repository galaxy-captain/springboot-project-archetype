package me.galaxy.archetype.web.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebErrorPageController {

    @RequestMapping("/errors")
    public String errors() throws Exception {
        throw new Exception("SpringBoot internal errors.");
    }

}
