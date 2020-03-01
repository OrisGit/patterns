package com.oris.edu.patterns.chain_of_responsibility;

import com.oris.edu.patterns.factory_method.Transport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class TransportFileWriter {
    private TransportFileWriter next;

    public abstract void printToFile(Transport transport) throws IOException;

    protected void checkNext(Transport transport) throws IOException {
        if (next != null) {
            next.printToFile(transport);
        }
    }

    protected void writeToFile(String fileName, String data) throws IOException {
        File file = createFile(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
            fileWriter.flush();
        }
    }

    private File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public TransportFileWriter setNext(TransportFileWriter next) {
        this.next = next;
        return next;
    }
}
