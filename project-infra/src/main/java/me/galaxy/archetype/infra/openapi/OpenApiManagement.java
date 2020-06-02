package me.galaxy.archetype.infra.openapi;

import com.fasterxml.jackson.core.type.TypeReference;
import me.galaxy.archetype.infra.utils.FileUtils;
import me.galaxy.archetype.infra.utils.JsonUtils;
import me.galaxy.archetype.infra.utils.MD5Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 4:13 下午
 **/
@Component
public class OpenApiManagement implements InitializingBean {

    private Map<String, String> holder;

    @Override
    public void afterPropertiesSet() throws Exception {
        String json = FileUtils.readJsonFile("classpath:openapi-config.json");
        List<Map<String, String>> applications = JsonUtils.parseJson(json, new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> application : applications) {
            this.holder.put(application.get("appId"), application.get("appSecret"));
        }
    }

    public boolean check(String appId, String signature, String timestamp) {

        if (!holder.containsKey(appId)) {
            return false;
        }

        String localSignature = MD5Utils.encode(appId + holder.get(appId) + timestamp);

        return localSignature.equals(signature);
    }

}