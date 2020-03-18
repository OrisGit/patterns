package com.oris.edu.patterns.command;

import com.oris.edu.patterns.factory_method.Car;
import com.oris.edu.patterns.factory_method.TransportUtil;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, IOException {
        Car tesla = (Car) TransportUtil.createInstance("Tesla", 10);
        tesla.addModel("Model X", 10000);
        tesla.addModel("Model Y", 120000);
        tesla.addModel("Model Z", 1220000);
        tesla.addModel("Model A", 50000);

        tesla.setCommand(new ColumnPrintCommand(tesla));
        try(OutputStream outputStream = getOutputStream("column.txt")){
            tesla.print(outputStream);
        }

        tesla.setCommand(new StringPrintCommand(tesla));
        try(OutputStream outputStream = getOutputStream("string.txt")){
            tesla.print(outputStream);
        }

    }

    private static OutputStream getOutputStream(String fileName) throws IOException {
        File file = createFile(fileName);
        return new FileOutputStream(file);
    }

    private static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
