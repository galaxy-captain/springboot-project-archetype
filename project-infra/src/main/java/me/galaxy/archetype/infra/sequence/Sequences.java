package me.galaxy.archetype.infra.sequence;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/30 6:11 下午
 **/
public class Sequences {

    private static final SequenceGenerator generator = new UUIDSequenceGenerator();

    public static String next() {
        return generator.next();
    }

}