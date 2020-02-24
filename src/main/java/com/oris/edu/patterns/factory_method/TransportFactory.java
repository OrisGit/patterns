package com.oris.edu.patterns.factory_method;

public interface TransportFactory {
    Transport createInstance(String brand, int modelsQuantity);
}
