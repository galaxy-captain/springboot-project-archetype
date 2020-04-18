package me.galaxy.archetype.web;

import me.galaxy.archetype.business.LookupCommandService;
import me.galaxy.archetype.business.LookupQueryService;
import me.galaxy.archetype.business.LookupService;
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
    private LookupQueryService lookupQueryService;

    @Autowired
    private LookupCommandService lookupCommandService;

    @PostMapping(path = "/{code}/query")
    public LookupRTO query(@PathVariable("code") String code, HttpServletResponse response) {
        lookupQueryService.queryLookupByCode();
        LookupRTO result = new LookupRTO();
        result.setCode(code);
        result.setName(code + "-query");
        return result;
    }

    @PostMapping(path = "/{code}/add")
    public String add(@PathVariable("code") String code) {
        lookupCommandService.addLookup();
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