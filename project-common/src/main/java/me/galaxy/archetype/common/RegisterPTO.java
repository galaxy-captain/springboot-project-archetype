package me.galaxy.archetype.common;

import lombok.Data;
import me.galaxy.archetype.common.base.DTO;

import java.util.Date;

@Data
public class RegisterPTO implements DTO.Parameter {

    private String account;

    private String password;

    private String name;

    private String sex;

    private Date birthday;

    private String position;

}
