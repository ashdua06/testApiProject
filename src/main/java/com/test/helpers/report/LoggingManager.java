package com.test.helpers.report;
import java.io.File;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggingManager {
    private static Logger logger = LogManager.getLogger(LoggingManager.class);
    public static File logXMLConfigFile;
    public static String templateFile;
    public static String templateDir;
    private static LoggerContext loggerContext;

    public LoggingManager() {
    }

    public static <T> Logger getLogger(Class<T> name) {
        Object logger = null;

        try {
            return LogManager.getLogger(name);
        } catch (LogConfigurationException var3) {
            var3.printStackTrace();
            return (Logger)logger;
        }
    }




}

