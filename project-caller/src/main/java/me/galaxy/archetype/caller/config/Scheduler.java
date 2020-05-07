package me.galaxy.archetype.caller.config;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Scheduler {

    @AliasFor(annotation = Component.class)
    String value() default "";

    boolean only() default true;

}
