package com.oris.edu.patterns.logger;

import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_RED;
import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_RESET;

public class DefaultLogger implements Logger{
    @Override
    public synchronized void printLog(ConsoleTextColor color, String message, Object... args) {
        System.out.println(color.getValue() + String.format(message, args) + ANSI_RESET.getValue());
    }

    @Override
    public synchronized void printLog(String message, Object... args) {
        System.out.println(String.format(message, args));
    }

    @Override
    public synchronized void printError(Exception e) {
        printLog(ANSI_RED, "Error: ");
        e.printStackTrace();
    }
}
