package com.tafu.baseSetup;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.tafu.helper.FileUtility;

public class AppLogger {
	
	public static Logger getLogger(final Class<?> cls) {
        boolean rootIsConfigured = Logger.getRootLogger().getAllAppenders().hasMoreElements();
        if (!rootIsConfigured) {
            BasicConfigurator.configure();
            Logger.getRootLogger().setLevel(Level.DEBUG);
            
            Appender appender = (Appender) Logger.getRootLogger().getAllAppenders().nextElement();
            appender.setLayout(new PatternLayout(" %-5p %d [%t] %C{1}: %m%n"));
            
            RollingFileAppender fileAppender = new RollingFileAppender();
            fileAppender.setLayout(new PatternLayout(" %d{ABSOLUTE} %-5p %d [%t] %C{1}: %m%n"));
            fileAppender.setFile("./OutputFiles/selenium.log");
            fileAppender.setMaxFileSize("5000KB");
            fileAppender.setAppend(false);
            fileAppender.setMaxBackupIndex(1);
            fileAppender.activateOptions();
            Logger.getRootLogger().addAppender(fileAppender);
        }

        return Logger.getLogger(cls);
    }
	
}
