package me.galaxy.archetype.infra.context;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:14 下午
 **/
public class LocalContext {

    private static final ThreadLocal<UserContext> holder = new ThreadLocal<>();

    private static final ThreadLocal<String> languageHolder = new ThreadLocal<>();

    public static void set(UserContext context) {
        holder.set(context);
    }

    public static UserContext get() {
        return holder.get();
    }

    public static void setLanguage(String language) {
        languageHolder.set(language);
    }

    public static String getLanguage() {
        return languageHolder.get();
    }

    public static void clear() {
        holder.remove();
        languageHolder.remove();
    }

}