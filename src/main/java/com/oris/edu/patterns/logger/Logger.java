package com.oris.edu.patterns.logger;

public interface Logger {
    void printLog(ConsoleTextColor color, String message, Object... args);

    void printLog(String message, Object... args);

    void printError(Exception e);
}
