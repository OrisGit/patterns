package com.oris.edu.patterns.logger;

public class LoggerFactory {
    private static Logger defaultLogger;

    public static Logger getLogger(){
        if(defaultLogger==null){
            defaultLogger = new DefaultLogger();
        }

        return defaultLogger;
    }

    public static Logger getLogger(Class clazz){
        return new WithClassLogger(clazz);
    }
}
