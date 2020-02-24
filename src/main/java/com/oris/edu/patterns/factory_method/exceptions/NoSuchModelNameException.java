package com.oris.edu.patterns.factory_method.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String modelName) {
        super(String.format("Model with name %s does not exist", modelName));
    }
}
