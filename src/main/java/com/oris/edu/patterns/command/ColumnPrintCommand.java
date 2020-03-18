package com.oris.edu.patterns.command;

import com.oris.edu.patterns.factory_method.Car;

import java.io.IOException;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ColumnPrintCommand implements Command {

    private final Car toPrint;

    public ColumnPrintCommand(Car toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public void execute(OutputStream outputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        String[] allModelNames = toPrint.getAllModelNames();
        for (int i = 0; i < allModelNames.length; i++) {
            if (i != 0) {
                builder.append("\n");
            }
            try {
                builder.append("Model: ").append(allModelNames[i]).append(" ")
                        .append("Price: ").append(toPrint.getModelPrice(allModelNames[i]));
            } catch (Exception ignored) {
            }
        }
        outputStream.write(builder.toString().getBytes(UTF_8));
        outputStream.flush();
    }
}
