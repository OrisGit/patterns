package com.oris.edu.patterns.factory_method;

public class CarFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int modelsQuantity) {
        return new Car(brand, modelsQuantity);
    }
}
