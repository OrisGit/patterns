package com.oris.edu.patterns.factory_method;

import com.oris.edu.patterns.command.Command;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.ModelPriceOutOfBoundsException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;
import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;
import com.oris.edu.patterns.visitor.Visitor;

import java.io.*;
import java.util.*;

import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_YELLOW;

public class Car implements Transport, Serializable{
    private String brand;
    private Model[] models;
    private int modelsQuantity;
    private Command command;

    private transient Logger log = LoggerFactory.getLogger(Car.class);

    public Car(String brand, int modelsQuantity) {
        this.brand = brand;
        this.models = new Model[modelsQuantity];
        this.modelsQuantity = 0;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void changeModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        log.printLog(ANSI_YELLOW, "Change model name from %s to %s", oldName, newName);
        if (oldName.equals(newName)) return;
        Model changedModel = null;
        for (Model model : models) {
            if (model == null) continue;
            if (model.name.equals(oldName)) {
                changedModel = model;
            } else if (model.name.equals(newName)) {
                throw new DuplicateModelNameException(newName);
            }
        }
        if (changedModel != null) {
            changedModel.name = newName;
        } else {
            throw new NoSuchModelNameException(oldName);
        }

    }

    public String[] getAllModelNames() {
        return Arrays.stream(models)
                .filter(Objects::nonNull)
                .map(model -> model.name)
                .toArray(String[]::new);
    }

    public Double[] getAllModelPrices() {
        return Arrays.stream(models)
                .filter(Objects::nonNull)
                .map(model -> model.price)
                .toArray(Double[]::new);
    }

    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        Optional<Model> model = findModel(modelName);
        if (model.isPresent()) {
            return model.get().price;
        }
        throw new NoSuchModelNameException(modelName);
    }

    public void changeModelPrice(String modelName, double newPrice) throws NoSuchModelNameException {
        log.printLog(ANSI_YELLOW, "Change model %s price to %f", modelName, newPrice);
        if (newPrice < 0) {
            throw new ModelPriceOutOfBoundsException(newPrice);
        }
        Optional<Model> model = findModel(modelName);
        if (model.isPresent()) {
            model.get().price = newPrice;
            return;
        }
        throw new NoSuchModelNameException(modelName);
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        log.printLog(ANSI_YELLOW, "Add model %s with price %f", name, price);
        Optional<Model> model = findModel(name);
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(price);
        }
        if (model.isPresent()) {
            throw new DuplicateModelNameException(name);
        }
        if (models.length <= modelsQuantity) {
            models = Arrays.copyOf(this.models, models.length + 1);
            models[models.length - 1] = new Model(name, price);
        } else {
            models[modelsQuantity] = new Model(name, price);
        }
        modelsQuantity++;
    }

    public void removeModel(String name) {
        log.printLog(ANSI_YELLOW, "Remove model %s", name);
        for (int i = 0; i < models.length; i++) {
            if (models[i].name.equals(name)) {
                System.arraycopy(models, i + 1, models, i, models.length - i - 1);
                models = Arrays.copyOf(models, models.length - 1);
                modelsQuantity--;
                return;
            }
        }
    }

    public int getModelsQuantity() {
        return modelsQuantity;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCar(this);
    }

    public Transport clone() {
        Car clone;
        try {
            clone = (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("The class does not support cloning", e);
        }
        //clone.brand = this.brand;
        //clone.modelsQuantity = this.modelsQuantity;
        clone.models = new Model[this.models.length];
        for (int i = 0; i < this.models.length; i++) {
            if (this.models[i] != null)
                clone.models[i] = new Model(this.models[i].name, this.models[i].price);
        }
        return clone;
    }

    private Optional<Model> findModel(String modelName) {
        return Arrays.stream(models)
                .filter(model -> model != null && model.name.equals(modelName))
                .findFirst();
    }

    @Override
    public String toString() {
        return "Car{\n" +
                "brand='" + brand + "\'\n" +
                ", models=" + Arrays.toString(models) + "\n" +
                ", modelsQuantity=" + modelsQuantity + "\n" +
                ", log=" + log +
                '}';
    }

    public void print(OutputStream outputStream) throws IOException {
        if (command != null)
            command.execute(outputStream);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Iterator<Model> iterator() {
        return new CarIterator(this);
    }

    //-------------- Memento ----------------------
    public Memento createMemento() throws IOException {
        Memento memento = new Memento();
        memento.setCar(this);
        return memento;
    }

    public void setMemento(Memento memento) throws IOException, ClassNotFoundException {
        Car car = memento.getCar();
        this.models = car.models;
        this.modelsQuantity = car.modelsQuantity;
        this.brand = car.brand;
    }

    public static class Model implements Cloneable, Serializable {
        String name;
        double price;

        Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        Model(Model original) {
            if (original != null) {
                this.name = original.name;
                this.price = original.price;
            }
        }

        public Model clone() {
            return new Model(this);
        }

        @Override
        public String toString() {
            return "Model{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    private static class CarIterator implements Iterator<Model> {
        Logger logger = LoggerFactory.getLogger(CarIterator.class);

        private Car car;
        private int currentPosition;

        private CarIterator(Car car) {
            this.car = car;
            this.currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            //logger.printLog("Has next");
            return currentPosition < car.modelsQuantity;
        }

        @Override
        public Model next() {
            //logger.printLog("Next");
            return car.models[currentPosition++];
        }
    }

    public static class Memento {
        private String backup;

        public void setCar(Car car) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
                oos.writeObject(car);
            }
            backup = Base64.getEncoder().encodeToString(baos.toByteArray());
        }

        public Car getCar() throws IOException, ClassNotFoundException {
            byte[] data = Base64.getDecoder().decode(backup);
            try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))){
                return (Car) ois.readObject();
            }
        }
    }
}