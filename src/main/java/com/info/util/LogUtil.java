package com.info.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author : yue
 * @Date : 2020/6/2 / 9:30
 * 日志
 */
public class LogUtil {
    static Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void info(String msg){
        rootLogger.info(msg);
    }

    public static void debug(String msg){
        rootLogger.debug(msg);
    }

    public static void error(String error){
        rootLogger.error(error);
    }

    public static void error(String msg,Exception e){
        rootLogger.error(msg,e);
    }

    public static void warn(String msg){
        rootLogger.warn(msg);
    }

}
