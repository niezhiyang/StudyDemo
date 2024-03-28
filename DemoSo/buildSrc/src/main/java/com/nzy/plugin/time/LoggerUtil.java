package com.nzy.plugin.time;

import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;

/**
 * @author niezhiyang
 * since 12/7/21
 */
public class LoggerUtil {
    private static Logger sLogger;

    public static void setLog(Logger logger) {
        sLogger = logger;
    }

    public static void e(String msg) {
        sLogger.log(LogLevel.ERROR, msg);
    }
}
