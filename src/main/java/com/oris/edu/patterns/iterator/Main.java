package com.oris.edu.patterns.iterator;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.TransportUtil;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException {
        Car tesla = (Car) TransportUtil.createInstance("Tesla", 10);
        tesla.addModel("Model X", 10000);
        tesla.addModel("Model Y", 120000);
        tesla.addModel("Model Z", 1220000);
        tesla.addModel("Model A", 50000);

        for (Iterator<Car.Model> it = tesla.iterator(); it.hasNext(); ) {
            Car.Model model = it.next();
            System.out.println(model);
        }
    }
}
