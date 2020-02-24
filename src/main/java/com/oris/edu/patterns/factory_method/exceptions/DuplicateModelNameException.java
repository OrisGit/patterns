package com.oris.edu.patterns.factory_method.exceptions;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String modelName) {
        super(String.format("Model with name %s already exist", modelName));
    }
}
