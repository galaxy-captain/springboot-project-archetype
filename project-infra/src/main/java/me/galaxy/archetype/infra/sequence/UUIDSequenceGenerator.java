package me.galaxy.archetype.infra.sequence;

import java.util.UUID;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/4/30 6:02 下午
 **/
public class UUIDSequenceGenerator implements SequenceGenerator {

    @Override
    public String next() {
        return uuid();
    }

    private String uuid() {
        String uuid = UUID.randomUUID().toString();
        String[] uuidSubs = uuid.split("-");
        return uuidSubs[2] + uuidSubs[1] + uuidSubs[0] + uuidSubs[3] + uuidSubs[4];
    }

}