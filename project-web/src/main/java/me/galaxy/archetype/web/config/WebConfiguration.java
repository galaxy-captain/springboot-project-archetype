
package me.galaxy.archetype.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
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
    private WebExceptionHandler webExceptionHandler;

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(webExceptionHandler);
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