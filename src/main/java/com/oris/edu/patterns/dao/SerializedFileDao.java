package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Transport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedFileDao<T extends Transport> extends AbstractFileDao<T> {

    public SerializedFileDao(String path) throws FileNotFoundException {
        super(path);
    }

    @Override
    protected void writeData(List<T> data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        }
    }

    @Override
    protected List<T> readData() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (ArrayList) ois.readObject();
        } catch (EOFException e){
            return new ArrayList<>();
        }
    }
}
