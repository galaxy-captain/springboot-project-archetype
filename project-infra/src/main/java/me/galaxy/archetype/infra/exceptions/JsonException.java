package me.galaxy.archetype.infra.exceptions;

/**
 * @Description
 * @Author  galaxy-captain
 * @Date 2020/3/23 2:05 下午
 **/
public class JsonException extends RuntimeException {

    public JsonException() {

    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}