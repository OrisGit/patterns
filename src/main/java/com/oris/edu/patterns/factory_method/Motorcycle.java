package com.oris.edu.patterns.factory_method;

import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.ModelPriceOutOfBoundsException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;
import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.util.Optional;

import static com.oris.edu.patterns.logger.ConsoleTextColor.ANSI_YELLOW;


public class Motorcycle implements Transport {
    private Logger log = LoggerFactory.getLogger(Motorcycle.class);

    private String brand;
    private Model head = new Model();
    private int size = 0;

    {
        head.prev = head;
        head.next = head;
    }

    public Motorcycle(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void changeModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        log.printLog(ANSI_YELLOW, "Change model name from %s to %s", oldName, newName);
        if (oldName.equals(newName)) return;
        Model changedModel = null;
        Model currentModel = head.next;
        do {
            if (currentModel.name.equals(oldName)) {
                changedModel = currentModel;
            } else if (currentModel.name.equals(newName)) {
                throw new DuplicateModelNameException(newName);
            }
            currentModel = currentModel.next;
        } while (currentModel != head);
        if (changedModel != null) {
            changedModel.name = newName;
        } else {
            throw new NoSuchModelNameException(oldName);
        }

    }

    @Override
    public String[] getAllModelNames() {
        String[] names = new String[size];
        Model currentModel = head.next;
        int counter = 0;
        do {
            names[counter++] = currentModel.name;
            currentModel = currentModel.next;
        } while (currentModel != head);
        return names;
    }

    @Override
    public Double[] getAllModelPrices() {
        Double[] prices = new Double[size];
        Model currentModel = head.next;
        int counter = 0;
        do {
            prices[counter++] = currentModel.price;
            currentModel = currentModel.next;
        } while (currentModel != head);
        return prices;
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        Optional<Model> model = findModel(modelName);
        if (model.isPresent()) {
            return model.get().price;
        }
        throw new NoSuchModelNameException(modelName);
    }

    @Override
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

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        log.printLog(ANSI_YELLOW, "Add model %s with price %f", name, price);
        Optional<Model> model = findModel(name);
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(price);
        }
        if (model.isPresent()) {
            throw new DuplicateModelNameException(name);
        }
        head.prev = head.prev.next = new Model(name, price, head.prev, head);
        size++;
    }

    @Override
    public void removeModel(String name) {
        log.printLog(ANSI_YELLOW, "Remove model %s", name);
        Optional<Model> model = findModel(name);
        if (model.isPresent()) {
            model.get().prev.next = model.get().next;
            model.get().next.prev = model.get().prev;
            size--;
        }

    }

    @Override
    public Transport clone() {
        Motorcycle clone;
        try {
            clone = (Motorcycle)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("The class does not support cloning", e);
        }
        //clone.brand = this.brand;
        //clone.size = this.size;
        clone.head = new Model();
        clone.head.prev = clone.head;
        clone.head.next = clone.head;
        Model current = this.head.next;
        do {
            clone.head.prev = clone.head.prev.next = new Model(current.name, current.price, clone.head.prev, clone.head);
            current = current.next;
        } while (current != this.head);
        return clone;
    }

    @Override
    public int getModelsQuantity() {
        return size;
    }

    private Optional<Model> findModel(String modelName) {
        if (size != 0) {
            Model current = head.next;
            do {
                if (current.name.equals(modelName)) {
                    return Optional.of(current);
                }
                current = current.next;
            } while (current != head);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Motorcycle{\nbrand='")
                .append(brand)
                .append("\',\nmodels=[");
        Model current = head.next;
        do {
            stringBuilder.append(current.toString()).append(", ");
            current = current.next;
        } while (current != head);

        stringBuilder.append("],\n modelsQuantity=")
                .append(size)
                .append("\n}");
        return stringBuilder.toString();
    }

    private class Model {
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model() {
        }

        public Model(String name, double price, Model prev, Model next) {
            this.name = name;
            this.price = price;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Model{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
