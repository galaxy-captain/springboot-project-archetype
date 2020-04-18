package me.galaxy.archetype.infra.auth;

import me.galaxy.archetype.infra.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:00 下午
 **/
@Component
public class AuthenticationComposite implements Authentication {

    private List<Authentication> delegates = Collections.emptyList();

    @Autowired(required = false)
    public void setDelegates(List<Authentication> delegates) {
        if (CollectionUtils.isNotEmpty(delegates)) {
            AnnotationAwareOrderComparator.sort(delegates);
            this.delegates = Collections.unmodifiableList(delegates);
        }
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

    public List<Authentication> getDelegates() {
        return this.delegates;
    }

}