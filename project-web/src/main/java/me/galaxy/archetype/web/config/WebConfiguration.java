
package me.galaxy.archetype.web.config;

import lombok.extern.slf4j.Slf4j;
import me.galaxy.archetype.web.config.session.SessionHandlerInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/18 10:16 上午
 **/
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private SessionHandlerInterceptor sessionHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionHandlerInterceptor).addPathPatterns("/**");
    }

    @Configuration
    public static class WebReturnConfiguration implements InitializingBean {

        @Autowired
        private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

        @Override
        public void afterPropertiesSet() throws Exception {
            List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
            List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
            if (handlerMethodReturnValueHandlers != null) {
                for (HandlerMethodReturnValueHandler returnValueHandler : handlerMethodReturnValueHandlers) {
                    if (returnValueHandler instanceof RequestResponseBodyMethodProcessor) {
                        returnValueHandlers.add(new WebReturnHandler((RequestResponseBodyMethodProcessor) returnValueHandler));
                    } else {
                        returnValueHandlers.add(returnValueHandler);
                    }
                }
                requestMappingHandlerAdapter.setReturnValueHandlers(Collections.unmodifiableList(returnValueHandlers));
            }
        }

    }

}