package com.oris.edu.patterns.factory_method;

import com.oris.edu.patterns.decorator.SynchronizedTransport;

import java.util.Arrays;

public class TransportUtil {

    private static TransportFactory factory = new CarFactory();

    public static void setFactory(TransportFactory factory) {
         TransportUtil.factory = factory;
    }

    public static Transport synchronizedTransport(Transport transport){
        return new SynchronizedTransport(transport);
    }

    public static Transport createInstance(String brand, int modelsQuantity){
        return factory.createInstance(brand, modelsQuantity);
    }

    public static double calculateAverageValueModelPrices(Transport transport){
        return Arrays.stream(transport.getAllModelPrices())
                .mapToDouble(value -> value)
                .average()
                .getAsDouble();
    }

    public static void printAllModels(Transport transport){
        System.out.println("\nAll models for "+transport.getBrand()+": ");
        Arrays.stream(transport.getAllModelNames())
                .forEach(System.out::println);
    }

    public static void printAllPrices(Transport transport){
        System.out.println("\nAll prices for "+transport.getBrand()+": ");
        Arrays.stream(transport.getAllModelPrices())
                .forEach(System.out::println);
    }
}
