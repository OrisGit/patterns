package com.oris.edu.patterns.visitor;

import com.oris.edu.patterns.factory_method.MotorcycleFactory;
import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.TransportUtil;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException {

        Transport tesla = TransportUtil.createInstance("Tesla", 10);
        tesla.addModel("Model X", 10000);
        tesla.addModel("Model Y", 120000);
        tesla.addModel("Model Z", 1220000);
        tesla.addModel("Model A", 50000);

        TransportUtil.setFactory(new MotorcycleFactory());
        Transport tesla2 = TransportUtil.createInstance("Tesla2", 10);
        tesla2.addModel("Model B", 10000);
        tesla2.addModel("Model C", 120000);
        tesla2.addModel("Model D", 1220000);

        Visitor visitor = new PrintVisitor();

        System.out.println("Car visitor: ");
        tesla.accept(visitor);

        System.out.println("\nMotorcycle visitor: ");
        tesla2.accept(visitor);
    }
}
