package me.galaxy.archetype.common;

import lombok.Data;
import me.galaxy.archetype.common.common.RTO;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/30 7:48 下午
 **/
@Data
public class LoginRTO implements RTO {

    private String token;

}