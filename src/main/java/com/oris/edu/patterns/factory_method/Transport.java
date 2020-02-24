package com.oris.edu.patterns.factory_method;

import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;

public interface Transport extends Cloneable{
    String getBrand();

    void setBrand(String brand);

    void changeModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;

    String[] getAllModelNames();

    Double[] getAllModelPrices();

    double getModelPrice(String modelName) throws NoSuchModelNameException;

    void changeModelPrice(String modelName, double newPrice) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

    Transport clone();

    int getModelsQuantity();
}
