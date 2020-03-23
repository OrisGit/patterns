package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.Motorcycle;

import java.io.FileNotFoundException;

public interface DaoFactory {
    Dao<Car> createCarDao(String path) throws FileNotFoundException;

    Dao<Motorcycle> createMotorcycleDao(String path) throws FileNotFoundException;
}
