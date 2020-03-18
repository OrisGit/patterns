package com.oris.edu.patterns.chain_of_responsibility;

import com.oris.edu.patterns.factory_method.Transport;

import java.io.IOException;

public class ColumnTransportFileWriter extends TransportFileWriter {
    @Override
    public void printToFile(Transport transport) throws IOException {
        if (transport.getModelsQuantity() > 3) {
            StringBuilder builder = new StringBuilder();
            String[] allModelNames = transport.getAllModelNames();
            for (int i = 0; i < allModelNames.length; i++) {
                if (i != 0) {
                    builder.append("\n");
                }
                try {
                    builder.append("Model: ").append(allModelNames[i]).append(" ")
                            .append("Price: ").append(transport.getModelPrice(allModelNames[i]));
                } catch (Exception ignored) {
                }
            }
            writeToFile("column.txt", builder.toString());
        } else {
            checkNext(transport);
        }
    }
}