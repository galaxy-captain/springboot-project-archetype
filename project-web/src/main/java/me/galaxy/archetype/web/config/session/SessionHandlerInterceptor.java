
package me.galaxy.archetype.web.config.session;

import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.infra.auth.Authentication;
import me.galaxy.archetype.infra.auth.AuthenticationComposite;
import me.galaxy.archetype.infra.exceptions.WebException;
import me.galaxy.archetype.infra.session.LocalContext;
import me.galaxy.archetype.infra.session.UserContext;
import me.galaxy.archetype.repo.User;
import me.galaxy.archetype.web.config.whitelist.WhiteListFilter;
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
@EnableConfigurationProperties({SessionProperties.class})
@Component
public class SessionHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionProperties sessionProperties;

    @Autowired
    private WhiteListFilter whiteListFilter;

    @Autowired
    private Authentication<User> authentication;

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

        authenticateRequest(url, token, language); // 鉴权

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalContext.clear();
    }

    private void authenticateRequest(String url, String token, String language) throws Exception {

        LocalContext.setLanguage(language);

        // 白名单
        boolean isWhiteList = whiteListFilter.check(url);
        if (isWhiteList) {
            return;
        }

        // 鉴权
        boolean isAuth = authentication.check(token);
        if (isAuth) {
            User user = authentication.lookup(token);
            UserContext context = new UserContext();
            context.setToken(token);
            context.setLanguage(language);
            context.setName(user.getName());
            LocalContext.set(context);
            return;
        }

        throw new WebException(WebErrors.AUTH_ERROR.getMsg(), WebErrors.AUTH_ERROR.getCode());
    }

}