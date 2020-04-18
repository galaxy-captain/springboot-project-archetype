package me.galaxy;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/17 10:54 下午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface ThriftClient {

    @AliasFor(attribute = "name")
    String value() default "";

    @AliasFor(attribute = "value")
    String name() default "";

    String host();

    String port();

    String timeout() default "0";

    Class<ThriftClientFactory>[] factory() default {};

}