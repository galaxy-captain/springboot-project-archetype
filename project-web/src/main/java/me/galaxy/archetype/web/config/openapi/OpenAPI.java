package me.galaxy.archetype.web.config.openapi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.lang.annotation.*;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 3:54 下午
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenAPI {



}