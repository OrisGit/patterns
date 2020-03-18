package com.oris.edu.patterns.visitor;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.Motorcycle;

public interface Visitor {
    void visitCar(Car car);
    void visitMotorcycle(Motorcycle motorcycle);
}
