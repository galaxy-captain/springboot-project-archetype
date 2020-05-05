package me.galaxy.archetype.common;

import lombok.Data;
import me.galaxy.archetype.common.base.DTO;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/4/30 7:48 下午
 **/
@Data
public class LoginRTO implements DTO.Result {

    private String token;

}