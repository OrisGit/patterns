package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Transport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Transport> {
    Optional<T> get(String brand) throws IOException, ClassNotFoundException;

    List<T> getAll() throws IOException, ClassNotFoundException;

    void save(T t) throws IOException, ClassNotFoundException;

    void update(T t) throws IOException, ClassNotFoundException;

    void delete(T t) throws IOException, ClassNotFoundException;
}
