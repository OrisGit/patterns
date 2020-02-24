package com.oris.edu.patterns.factory_method.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(double price) {
        super("The price of a model cannot be negative");
    }
}
