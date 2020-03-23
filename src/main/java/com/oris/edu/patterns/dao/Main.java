package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.*;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Exception {
        runForCar();
        runForMotorcycle();
    }

    private static void runForMotorcycle() throws IOException, NoSuchModelNameException, DuplicateModelNameException, ClassNotFoundException {
        TransportUtil.setFactory(new MotorcycleFactory());
        DaoFactory serializedFileDaoFactory = new SerializedFileDaoFactory();
        DaoFactory textFileDaoFactory = new TextFileDaoFactory();
        Dao<Motorcycle> motorcycleSerializedFileDao = serializedFileDaoFactory.createMotorcycleDao(updateFile("D:/Temp/motorcycleSerialized.db"));
        Dao<Motorcycle> motorcycleTextFileDao = textFileDaoFactory.createMotorcycleDao(updateFile("D:/Temp/motorcycleText.db"));
        System.out.println("\n\nMotorcycle serialized\n");
        runTest(motorcycleSerializedFileDao);
        System.out.println("\n\nMotorcycle text\n");
        runTest(motorcycleTextFileDao);
    }

    private static <T extends Transport> void runTest(Dao<T> dao) throws DuplicateModelNameException, IOException, ClassNotFoundException, NoSuchModelNameException {
        Transport transport1 = TransportUtil.createInstance("Tesla", 10);
        transport1.addModel("Model S", 2000000);
        transport1.addModel("Model X", 3000000);
        transport1.addModel("Model A", 1000000);

        Transport transport2 = TransportUtil.createInstance("Ferrari", 10);
        transport2.addModel("Spider", 2000000);
        transport2.addModel("California", 3000000);
        transport2.addModel("Speciale", 1000000);

        dao.save((T) transport1);
        dao.save((T) transport2);

        Optional<T> ferrari = dao.get("Ferrari");
        System.out.println("Get: ");
        System.out.println(ferrari.get().toString());

        transport2.changeModelPrice("Spider", 400);

        dao.update((T) transport2);

        System.out.println("GetAll after update: ");
        List<T> all = dao.getAll();
        for (T t : all) {
            System.out.println(t.toString());
        }

        dao.delete((T) transport1);

        System.out.println("GetAll after delete: ");
        all = dao.getAll();
        for (T t : all) {
            System.out.println(t.toString());
        }

    }

    private static void runForCar() throws IOException, NoSuchModelNameException, DuplicateModelNameException, ClassNotFoundException {
        TransportUtil.setFactory(new CarFactory());
        DaoFactory serializedFileDaoFactory = new SerializedFileDaoFactory();
        DaoFactory textFileDaoFactory = new TextFileDaoFactory();
        Dao<Car> carSerializedFileDao = serializedFileDaoFactory.createCarDao(updateFile("D:/Temp/carSerialized.db"));
        Dao<Car> carTextFileDao = textFileDaoFactory.createCarDao(updateFile("D:/Temp/carText.db"));
        System.out.println("\n\nCar serialized\n");
        runTest(carSerializedFileDao);
        System.out.println("\n\nCar text\n");
        runTest(carTextFileDao);
    }

    private static void test(TransportFactory transportFactory) throws Exception {


    }

    private static String updateFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return path;
    }
}
