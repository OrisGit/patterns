package com.oris.edu.patterns.logger;

import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_RED;
import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_RESET;

public class WithClassLogger implements Logger {
    private final String classNamePrefix;

    public WithClassLogger(Class clazz) {
        this.classNamePrefix = "[" + clazz.getName() + "]";
    }

    @Override
    public void printLog(ConsoleTextColor color, String message, Object... args) {
        System.out.println(color.getValue() + classNamePrefix + " " + String.format(message, args) + ANSI_RESET.getValue());
    }

    @Override
    public void printLog(String message, Object... args) {
        System.out.println(classNamePrefix + " " + String.format(message, args));
    }

    @Override
    public void printError(Exception e) {
        printLog(ANSI_RED, "Error: ");
        e.printStackTrace();
    }
}
