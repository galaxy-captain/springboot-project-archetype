package me.galaxy.archetype.web;

import me.galaxy.archetype.common.LoginPTO;
import me.galaxy.archetype.common.LogoutPTO;
import me.galaxy.archetype.common.UserRTO;
import me.galaxy.archetype.infra.auth.Authorization;
import me.galaxy.archetype.infra.utils.DateUtils;
import me.galaxy.archetype.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public UserRTO login(@RequestBody LoginPTO pto) {

        User user = authorization.login(pto.getAccount(), pto.getPassword());

        UserRTO rto = new UserRTO();
        rto.setName(user.getName());
        rto.setSex(user.getSex());
        rto.setAge(DateUtils.getInterval(user.getBirthday()) / 365);
        rto.setBirthday(user.getBirthday());
        rto.setPosition(user.getPosition());

        return rto;
    }

    @PostMapping("/logout")
    public UserRTO logout(@RequestBody LogoutPTO pto) {

        UserRTO rto = new UserRTO();
        return rto;
    }

}