package com.oris.edu.patterns.singleton;

import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.util.Properties;

import static com.oris.edu.patterns.singleton.PropertiesLoader.*;
public class Main {
    private static Logger log = LoggerFactory.getLogger();

    public static void main(String[] args) {
        //create new object
        Properties properties1 = load();

        //use old object
        Properties properties2 = load();

        if(properties1 == properties2){
            log.printLog("Objects are identical");
        }

        log.printLog("someProperty1=%s", properties2.getProperty("someProperty1"));
        log.printLog("someProperty2=%s", properties2.getProperty("someProperty2"));

    }
}
