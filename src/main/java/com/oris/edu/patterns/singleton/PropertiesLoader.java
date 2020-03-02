package com.oris.edu.patterns.singleton;

import com.oris.edu.patterns.logger.ConsoleTextColor;
import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesLoader {
    private static Properties properties;
    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private PropertiesLoader(){};

    public  synchronized static Properties load(){
        if(properties == null){
            logger.printLog(ConsoleTextColor.ANSI_YELLOW,"Create new object");
            properties = new Properties();
            ClassLoader classLoader = PropertiesLoader.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("config.properties");
            try {
                properties.load(is);
            } catch (IOException e) {
                properties = null;
                throw new RuntimeException("The file was not found or is not in the correct format");
            }
        }

        return properties;
    }
}
