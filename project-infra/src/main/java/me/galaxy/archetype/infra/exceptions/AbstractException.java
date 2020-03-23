package me.galaxy.archetype.infra.exceptions;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 10:04 下午
 **/
public abstract class AbstractException extends RuntimeException implements IException{

    protected String code;

    protected String logLevel;

    protected boolean isDisplay;

    public AbstractException(String message, String code, String logLevel, boolean isDisplay) {
        super(message);
        this.code = code;
        this.logLevel = logLevel;
        this.isDisplay = isDisplay;
    }

    public AbstractException(Throwable cause, String message, String code, String logLevel, boolean isDisplay) {
        super(message, cause);
        this.code = code;
        this.logLevel = logLevel;
        this.isDisplay = isDisplay;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getCode() {
        return code;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

}