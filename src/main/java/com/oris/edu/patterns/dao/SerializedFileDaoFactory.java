package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.CarFactory;
import com.oris.edu.patterns.factory_method.Motorcycle;
import com.oris.edu.patterns.factory_method.MotorcycleFactory;

import java.io.FileNotFoundException;

public class SerializedFileDaoFactory implements DaoFactory {
    @Override
    public Dao<Car> createCarDao(String path) throws FileNotFoundException {
        return new SerializedFileDao<>(path);
    }

    @Override
    public Dao<Motorcycle> createMotorcycleDao(String path) throws FileNotFoundException {
        return new SerializedFileDao<>(path);
    }
}
