package me.galaxy.archetype.common.lookup;

import lombok.Data;
import me.galaxy.archetype.common.base.DTO;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/21 9:01 上午
 **/
@Data
public class LookupRTO implements DTO.Result {

    private String code;

    private String name;

}