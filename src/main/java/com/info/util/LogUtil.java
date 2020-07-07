package com.info.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	static Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	public static void info(String msg) {
		rootLogger.info(msg);
	}

	public static void debug(String msg) {
		rootLogger.debug(msg);
	}


	public static void error(String msg) {
		rootLogger.error(msg);
	}

	public static void error(String msg, Exception e) {
		rootLogger.error(msg, e);
	}

	public static void warn(String msg) {
		rootLogger.warn(msg);
	}
}
