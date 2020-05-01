package me.galaxy.archetype.infra.auth;

import me.galaxy.archetype.infra.utils.CollectionUtils;
import me.galaxy.archetype.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:00 下午
 **/
@Primary
@Component
public class AuthenticationComposite implements Authentication<User> {

    private List<Authentication> delegates = Collections.emptyList();

    @Autowired(required = false)
    public void setDelegates(List<Authentication> delegates) {
        if (CollectionUtils.isNotEmpty(delegates)) {
            AnnotationAwareOrderComparator.sort(delegates);
            this.delegates = Collections.unmodifiableList(delegates);
        }
    }

    @Override
    public String register(User obj) {
        for (Authentication delegate : this.delegates) {
            String token = delegate.register(obj);
            if (token != null) {
                return token;
            }
        }
        throw new NullPointerException("Authentication session register failed.");
    }

    @Override
    public User lookup(String token) {
        for (Authentication delegate : this.delegates) {
            User obj = (User) delegate.lookup(token);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public boolean check(String token) {
        for (Authentication delegate : this.delegates) {
            if (delegate.check(token)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(String token) {
        for (Authentication delegate : this.delegates) {
            delegate.remove(token);
        }
    }

    public List<Authentication> getDelegates() {
        return this.delegates;
    }

}