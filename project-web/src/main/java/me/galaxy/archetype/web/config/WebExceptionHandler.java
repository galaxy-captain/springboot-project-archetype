package me.galaxy.archetype.web.config;

import lombok.extern.slf4j.Slf4j;
import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.common.common.WebResult;
import me.galaxy.archetype.infra.exceptions.AbstractException;
import me.galaxy.archetype.infra.exceptions.IException;
import me.galaxy.archetype.infra.exceptions.ServerException;
import me.galaxy.archetype.infra.session.SessionHolder;
import me.galaxy.archetype.infra.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Controller异常信息处理器
 * @Author galaxy-captain
 * @Date 2020/3/17 11:06 下午
 **/
@Slf4j
@Component
public class WebExceptionHandler implements HandlerExceptionResolver {

    @Value("${spring.profiles.active:prd}")
    private String env;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (!(ex instanceof AbstractException)) {
                ex = new ServerException(ex, ex.getMessage(), WebErrors.UNKNOWN_ERROR.getCode());
            }

            AbstractException absEx = (AbstractException) ex;
            String code = absEx.getCode();
            String message = absEx.getMessage();
            String logLevel = absEx.getLogLevel();
            boolean isDisplay = absEx.isDisplay();

            printExceptionLog(logLevel, absEx);

            WebResult result = new WebResult();
            result.setCode(code);
            if (isDebugDisplay() || isDisplay) {
                result.setMsg(message);
            } else {
                result.setMsg(WebErrors.SERVER_ERROR.getMsg());
            }

            String json = JsonUtils.toJsonString(result);

            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (IOException e) {
                log.warn("Failed to write response body.", e);
            }

            return new ModelAndView();
        } finally {
            SessionHolder.clear();
        }
    }

    private void printExceptionLog(String logLevel, Exception e) {

        String message = "Print exception cause log";
        Throwable cause = e.getCause() == null ? e : e.getCause();

        switch (logLevel) {
            case IException.DEBUG:
                log.debug(message, cause);
                break;
            case IException.INFO:
                log.info(message, cause);
                break;
            case IException.WARN:
                log.warn(message, cause);
                break;
            case IException.ERROR:
                log.error(message, cause);
                break;
            default:
                log.warn(message, cause);
                break;
        }
    }

    private boolean isDebugDisplay() {
        return env.startsWith("dev") || env.startsWith("test");
    }

}