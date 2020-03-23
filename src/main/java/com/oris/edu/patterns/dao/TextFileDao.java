package com.oris.edu.patterns.dao;

import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.TransportFactory;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileDao<T extends Transport> extends AbstractFileDao<T> {

    private static final String BRAND = "brand:";
    private static final String SIZE = "size:";
    private static final String MODELS = "models:";
    private static final String MODEL_NAME = "name:";
    private static final String MODEL_PRICE = "price:";

    private final TransportFactory transportFactory;

    public TextFileDao(String path, TransportFactory transportFactory) throws FileNotFoundException {
        super(path);
        this.transportFactory = transportFactory;
    }

    @Override
    protected void writeData(List<T> data) throws IOException {
        String serializedData = serializeData(data);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(serializedData);
        }
    }

    private String serializeData(List<T> data) {
        StringBuilder builder = new StringBuilder();
        for (T transport : data) {
            builder.append(BRAND).append(transport.getBrand()).append(" ");
            builder.append(SIZE).append(transport.getModelsQuantity()).append(" ");
            builder.append(MODELS);
            String[] allModelNames = transport.getAllModelNames();
            Double[] allModelPrices = transport.getAllModelPrices();
            for (int i = 0; i < allModelNames.length; i++) {
                builder.append("[").append(MODEL_NAME).append(allModelNames[i])
                        .append(" ").append(MODEL_PRICE).append(allModelPrices[i]).append("]");
                if(i != allModelNames.length-1){
                    builder.append(",");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    protected List<T> readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Stream<String> serializedData = reader.lines();
        return deserializeData(serializedData);
    }

    private List<T> deserializeData(Stream<String> serializedData) {
        return serializedData.map(line -> {
            try {
                String brand = line.substring(line.indexOf(BRAND)+BRAND.length(), line.indexOf(SIZE)-1);
                String size = line.substring(line.indexOf(SIZE)+SIZE.length(), line.indexOf(MODELS)-1);
                String modelsString = line.substring(line.indexOf(MODELS) + MODELS.length());
                String[] models = modelsString.split(",");
                T transport = (T) transportFactory.createInstance(brand, Integer.parseInt(size));
                for (String model : models) {
                    String name = model.substring(model.indexOf(MODEL_NAME) + MODEL_NAME.length(), model.indexOf(MODEL_PRICE) - 1);
                    String price = model.substring(model.indexOf(MODEL_PRICE) + MODEL_PRICE.length(), model.length() - 2);
                    transport.addModel(name, Double.parseDouble(price));
                }
                return transport;
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
