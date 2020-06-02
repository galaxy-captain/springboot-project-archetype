package me.galaxy.archetype.web.config.openapi;

import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.infra.exceptions.ServerException;
import me.galaxy.archetype.infra.exceptions.WebException;
import me.galaxy.archetype.infra.openapi.OpenApiManagement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 3:53 下午
 **/
@Aspect
@Component
public class OpenApiRequestAspect {

    @Autowired
    private OpenApiManagement openApiManagement;

    @Around("@annotation(me.galaxy.archetype.web.config.openapi.OpenAPI)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        OpenApiRequest request = null;
        for (Object arg : args) {
            if (arg instanceof OpenApiRequest) {
                request = (OpenApiRequest) arg;
                break;
            }
        }

        if (request == null) {
            String methodName = pjp.getSignature().getName();
            throw new ServerException("OpenAPI接口方法[" + methodName + "]中@RequestBody的类型必须为[me.galaxy.archetype.web.config.openapi.OpenApiParameter]");
        }

        boolean isOk = openApiManagement.check(request.getAppId(), request.getSignature(), request.getTimestamp());

        if (!isOk) {
            throw new WebException(WebErrors.OPEN_API_ERROR);
        }

        return pjp.proceed();
    }

}