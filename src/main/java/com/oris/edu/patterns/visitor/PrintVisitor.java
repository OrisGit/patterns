package com.oris.edu.patterns.visitor;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.Motorcycle;
import com.oris.edu.patterns.factory_method.Transport;

public class PrintVisitor implements Visitor{
    @Override
    public void visitCar(Car car) {
        print(car, ", ");
    }

    @Override
    public void visitMotorcycle(Motorcycle motorcycle) {
        print(motorcycle, "\n");
    }

    private void print(Transport transport, String delimiter){
        StringBuilder builder = new StringBuilder();
        String[] allModelNames = transport.getAllModelNames();
        for (int i = 0; i < allModelNames.length; i++) {
            if (i != 0) {
                builder.append(delimiter);
            }
            try {
                builder.append("Model: ").append(allModelNames[i]).append(" ")
                        .append("Price: ").append(transport.getModelPrice(allModelNames[i]));
            } catch (Exception ignored) {
            }
        }
        System.out.println(builder.toString());
    }
}
