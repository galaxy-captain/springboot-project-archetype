package me.galaxy.archetype.web.config.whitelist;

import me.galaxy.archetype.infra.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:33 下午
 **/
@Primary
@Component
public class WhiteListFilterComposite  implements WhiteListFilter{

    private final List<WhiteListFilter> delegates = new LinkedList<>();

    @Autowired(required = false)
    public void setDelegates(List<WhiteListFilter> delegates) {
        if (CollectionUtils.isNotEmpty(delegates)) {
            AnnotationAwareOrderComparator.sort(delegates);
            this.delegates.addAll(delegates);
        }
    }

    @Override
    public boolean check(String url) {
        for (WhiteListFilter delegate : this.delegates) {
            if (delegate.check(url)) {
                return true;
            }
        }
        return false;
    }

    public List<WhiteListFilter> getDelegates() {
        return this.delegates;
    }

}