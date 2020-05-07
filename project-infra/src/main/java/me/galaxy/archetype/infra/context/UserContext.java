package me.galaxy.archetype.infra.context;

import lombok.Data;

@Data
public class UserContext {

    private String token;

    private String language;

    private String name;

}
