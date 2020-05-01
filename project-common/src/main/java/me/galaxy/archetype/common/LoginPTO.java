package me.galaxy.archetype.common;

import lombok.Data;
import me.galaxy.archetype.common.base.DTO;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 10:37 下午
 **/
@Data
public class LoginPTO implements DTO.Parameter {

    private String account;

    private String password;

}