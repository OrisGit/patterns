package com.oris.edu.patterns.decorator;

import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;

public class SynchronizedTransport extends TransportDecorator {
    public SynchronizedTransport(Transport transport) {
        super(transport);
    }

    @Override
    public synchronized void setBrand(String brand) {
        super.setBrand(brand);
    }

    @Override
    public synchronized void changeModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        super.changeModelName(oldName, newName);
    }

    @Override
    public synchronized void changeModelPrice(String modelName, double newPrice) throws NoSuchModelNameException {
        super.changeModelPrice(modelName, newPrice);
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        super.addModel(name, price);
    }

    @Override
    public synchronized void removeModel(String name) throws NoSuchModelNameException {
        super.removeModel(name);
    }
}
