package me.galaxy.archetype.web;

import me.galaxy.archetype.infra.outer.TestFeign;
import me.galaxy.archetype.common.lookup.LookupRTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 10:18 上午
 **/
@RestController
@RequestMapping(path = "/lookup")
public class LookupController {

    @Autowired
    private TestFeign testFeign;

    private Random random = new Random();

    @GetMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep(2000);
        return testFeign.test();
    }

    @GetMapping("/test1")
    public String test1() throws InterruptedException {
        Thread.sleep(200);
        return "hello world";
    }

    @PostMapping(path = "/{code}/query")
    public LookupRTO query(@PathVariable("code") String code, HttpServletResponse response) {
        LookupRTO result = new LookupRTO();
        result.setCode(code);
        result.setName(code + "-query");
        return result;
    }

    @PostMapping(path = "/{code}/add")
    public String add(@PathVariable("code") String code) {
        return code + "-add";
    }

    @PostMapping(path = "/{code}/remove")
    public String remove(@PathVariable("code") String code) {
        return code + "-remove";
    }

    @PostMapping(path = "/{code}/modify")
    public String modify(@PathVariable("code") String code) {
        return code + "-modify";
    }

}