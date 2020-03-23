package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Transport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractFileDao<T extends Transport> implements Dao<T>{
    protected final File file;

    public AbstractFileDao(String path) throws FileNotFoundException {
        this.file = new File(path);
        if(!this.file.exists()){
            throw new FileNotFoundException("Файл не найден");
        }
    }

    @Override
    public Optional<T> get(String brand) throws IOException, ClassNotFoundException {
        List<T> ts = readData();
        for (T t : ts) {
            if(t.getBrand().equals(brand)){
                return Optional.of(t);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<T> getAll() throws IOException, ClassNotFoundException {
        return readData();
    }

    @Override
    public void save(T t) throws IOException, ClassNotFoundException {
        List<T> ts = readData();
        ts.add(t);
        writeData(ts);
    }

    @Override
    public void update(T t) throws IOException, ClassNotFoundException {
        List<T> ts = readData();
        for (int i = 0; i < ts.size(); i++) {
            if(ts.get(i).getBrand().equals(t.getBrand())){
                ts.set(i, t);
                writeData(ts);
                return;
            }
        }
    }

    @Override
    public void delete(T t) throws IOException, ClassNotFoundException {
        List<T> ts = readData();
        for (int i = 0; i < ts.size(); i++) {
            if(ts.get(i).getBrand().equals(t.getBrand())){
                ts.remove(i);
                writeData(ts);
                return;
            }
        }
    }

    protected abstract void writeData(List<T> data) throws IOException;
    protected abstract List<T> readData() throws IOException, ClassNotFoundException;
}
