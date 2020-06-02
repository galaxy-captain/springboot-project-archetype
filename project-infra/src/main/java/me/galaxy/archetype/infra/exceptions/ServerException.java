
package me.galaxy.archetype.infra.exceptions;

import me.galaxy.archetype.common.error.WebErrors;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 10:55 下午
 **/
public class ServerException extends AbstractException {

    public ServerException(WebErrors errors) {
        super(errors.getMsg(), errors.getCode(), WARN, false);
    }

    public ServerException(String message) {
        super(message, WebErrors.SERVER_ERROR.getCode(), WARN, false);
    }

    public ServerException(String message, String code) {
        super(message, code, WARN, false);
    }

    public ServerException(Throwable cause, String message, String code) {
        super(cause, message, code, WARN, false);
    }

    public ServerException(String message, String code, String logLevel) {
        super(message, code, logLevel, false);
    }

    public ServerException(Throwable cause, String message, String code, String logLevel) {
        super(cause, message, code, logLevel, false);
    }

    @Override
    public String getFullMessage() {
        return null;
    }

}