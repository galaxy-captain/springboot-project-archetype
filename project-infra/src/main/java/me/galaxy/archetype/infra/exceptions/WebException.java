
package me.galaxy.archetype.infra.exceptions;

import me.galaxy.archetype.common.error.WebErrors;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 10:55 下午
 **/
public class WebException extends AbstractException {

    public WebException(WebErrors errors) {
        this(errors.getMsg(), errors.getCode());
    }

    public WebException(WebErrors errors, String message) {
        this(message, errors.getCode());
    }

    public WebException(String message, String code) {
        super(message, code, INFO, true);
    }

    public WebException(Throwable cause, String message, String code) {
        super(cause, message, code, INFO, true);
    }

    public WebException(String message, String code, String logLevel) {
        super(message, code, logLevel, true);
    }

    public WebException(Throwable cause, String message, String code, String logLevel) {
        super(cause, message, code, logLevel, true);
    }

    @Override
    public String getFullMessage() {
        return null;
    }

}