package com.oris.edu.patterns.decorator;

import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;
import com.oris.edu.patterns.visitor.Visitor;

public class TransportDecorator implements Transport {

    private Transport transport;

    public TransportDecorator(Transport transport){
        this.transport = transport;
    }

    @Override
    public String getBrand() {
        return transport.getBrand();
    }

    @Override
    public void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public void changeModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        transport.changeModelName(oldName, newName);
    }

    @Override
    public String[] getAllModelNames() {
        return transport.getAllModelNames();
    }

    @Override
    public Double[] getAllModelPrices() {
        return transport.getAllModelPrices();
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        return transport.getModelPrice(modelName);
    }

    @Override
    public void changeModelPrice(String modelName, double newPrice) throws NoSuchModelNameException {
        transport.changeModelPrice(modelName, newPrice);
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        transport.addModel(name, price);
    }

    @Override
    public void removeModel(String name) throws NoSuchModelNameException {
        transport.removeModel(name);
    }

    @Override
    public Transport clone() {
        return transport.clone();
    }

    @Override
    public int getModelsQuantity() {
        return transport.getModelsQuantity();
    }

    @Override
    public void accept(Visitor visitor) {
        transport.accept(visitor);
    }
}
