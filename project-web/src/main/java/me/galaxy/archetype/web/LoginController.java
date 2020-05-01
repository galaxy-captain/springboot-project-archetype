package me.galaxy.archetype.web;

import me.galaxy.archetype.common.LoginPTO;
import me.galaxy.archetype.common.LoginRTO;
import me.galaxy.archetype.common.LogoutRTO;
import me.galaxy.archetype.infra.auth.Authorization;
import me.galaxy.archetype.infra.session.LocalContext;
import me.galaxy.archetype.infra.session.UserContext;
import me.galaxy.archetype.web.config.session.SessionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 10:35 下午
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private Authorization authorization;

    @Autowired
    private SessionProperties sessionProperties;

    @RequestMapping
    public LoginRTO login(@RequestBody LoginPTO pto, HttpServletResponse response) {

        // 登录系统
        String token = authorization.login(pto.getAccount(), pto.getPassword());

        // 设置响应Cookie
        Cookie cookie = new Cookie(sessionProperties.getToken(), token);
        cookie.setMaxAge(60 * 60 * 12);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        LoginRTO rto = new LoginRTO();
        rto.setToken(token);

        return rto;
    }

    @PostMapping("/logout")
    public LogoutRTO logout() {

        UserContext context = LocalContext.get();
        String token = context.getToken();
        authorization.logout(token);

        LogoutRTO rto = new LogoutRTO();
        return rto;
    }

}