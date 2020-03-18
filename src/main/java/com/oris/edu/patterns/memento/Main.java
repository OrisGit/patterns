package com.oris.edu.patterns.memento;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.TransportUtil;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;

import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, IOException, NoSuchModelNameException, ClassNotFoundException {
        Car tesla = (Car) TransportUtil.createInstance("Tesla", 10);
        tesla.addModel("Model X", 10000);
        tesla.addModel("Model Y", 120000);
        tesla.addModel("Model Z", 1220000);
        tesla.addModel("Model A", 50000);

        Car.Memento memento = tesla.createMemento();

        tesla.changeModelName("Model X", "Model U");

        System.out.println(tesla);

        tesla.setMemento(memento);

        System.out.println(tesla);
    }
}
