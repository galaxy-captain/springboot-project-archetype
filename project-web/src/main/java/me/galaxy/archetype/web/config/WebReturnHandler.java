package me.galaxy.archetype.web.config;

import lombok.extern.slf4j.Slf4j;
import me.galaxy.archetype.common.base.WebResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;

/**
 * @Description Controller返回值处理器
 * @Author galaxy-captain
 * @Date 2020/3/21 9:11 上午
 **/
@Slf4j
public class WebReturnHandler implements HandlerMethodReturnValueHandler {

    private RequestResponseBodyMethodProcessor handler;

    public WebReturnHandler(RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor) {
        this.handler = requestResponseBodyMethodProcessor;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return handler.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {

        if (!(returnValue instanceof WebResult)) {
            returnValue = new WebResult(returnValue);
        }

        handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

}