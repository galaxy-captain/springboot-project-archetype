package me.galaxy.archetype.infra.exceptions;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/17 10:56 下午
 **/
public interface IException {

    String DEBUG = "DEBUG";

    String INFO = "INFO";

    String WARN = "WARN";

    String ERROR = "ERROR";

    String getFullMessage();

}