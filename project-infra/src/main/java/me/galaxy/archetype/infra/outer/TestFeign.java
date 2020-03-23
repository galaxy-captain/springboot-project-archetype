/*
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 */
package me.galaxy.archetype.infra.outer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/3/23 7:07 下午
 **/
@FeignClient(name = "testFeign", url = "http://localhost:8080/lookup")
public interface TestFeign {

    @GetMapping(path = "/test1")
    String test();

}