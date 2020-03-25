
package me.galaxy.archetype.web.config.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object method) throws Exception {
        try {
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
            authenticateRequest(url, token, language);
            return true; // 鉴权成功
        } catch (Exception e) {
            authenticateFailed(response, e);
            return false; // 鉴权失败
        }
    }

    public void authenticateRequest(String url, String token, String language) throws Exception {

    }

    private void authenticateFailed(HttpServletResponse response, Exception e) {

    }

}