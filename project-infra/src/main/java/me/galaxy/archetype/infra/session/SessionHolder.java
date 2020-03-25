package me.galaxy.archetype.infra.session;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:14 下午
 **/
public class SessionHolder {

    private static final ThreadLocal<Object> userHolder = new ThreadLocal<>();

    private static final ThreadLocal<Object> languageHolder = new ThreadLocal<>();

    public static void setUser(Object user) {
        userHolder.set(user);
    }

    public static <T> T getUser() {
        return (T) userHolder.get();
    }

    public static void setLanguage(Object language) {
        languageHolder.set(language);
    }

    public static <T> T getLanguage() {
        return (T) languageHolder.get();
    }

    public static void clear() {
        userHolder.remove();
        languageHolder.remove();
    }

}