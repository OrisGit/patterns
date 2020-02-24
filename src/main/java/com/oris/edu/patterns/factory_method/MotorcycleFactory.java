package com.oris.edu.patterns.factory_method;

public class MotorcycleFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int modelsQuantity) {
        return new Motorcycle(brand);
    }
}
