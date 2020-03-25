
package me.galaxy.archetype.web.config.session;

import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.infra.auth.AuthenticationComposite;
import me.galaxy.archetype.infra.exceptions.WebException;
import me.galaxy.archetype.infra.session.SessionHolder;
import me.galaxy.archetype.web.config.whitelist.WhiteListFilterComposite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/24 12:22 上午
 **/
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(value = SessionProperties.class)
@Component
public class SessionHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionProperties sessionProperties;

    @Autowired
    private WhiteListFilterComposite whiteListFilterComposite;

    @Autowired
    private AuthenticationComposite authenticationComposite;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object method) throws Exception {
        String url = request.getRequestURI(); // 访问路径
        String token = null; // 授权标识
        String language = "zh"; // 语言环境
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (sessionProperties.getToken().equalsIgnoreCase(cookieName)) {
                    token = cookie.getValue();
                } else if (sessionProperties.getLanguage().equalsIgnoreCase(cookieName)) {
                    language = cookie.getValue();
                }
            }
        }

        // 鉴权
        authenticateRequest(url, token, language);

        return true; // 鉴权成功
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SessionHolder.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SessionHolder.clear();
    }

    private void authenticateRequest(String url, String token, String language) throws Exception {

        boolean isWhiteList = whiteListFilterComposite.check(url);
        if (isWhiteList) {
            return;
        }

        boolean isAuth = authenticationComposite.check(token);
        if (isAuth) {
            return;
        }

        throw new WebException(WebErrors.AUTH_ERROR.getMsg(), WebErrors.AUTH_ERROR.getCode());
    }

}