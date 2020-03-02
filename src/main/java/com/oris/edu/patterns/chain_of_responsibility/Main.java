package com.oris.edu.patterns.chain_of_responsibility;

import com.oris.edu.patterns.factory_method.Transport;
import com.oris.edu.patterns.factory_method.TransportUtil;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, IOException {
        Transport tesla = TransportUtil.createInstance("Tesla", 10);
        tesla.addModel("Model X", 10000);
        tesla.addModel("Model Y", 120000);
        tesla.addModel("Model Z", 1220000);
        tesla.addModel("Model A", 50000);

        Transport tesla2 = TransportUtil.createInstance("Tesla2", 10);
        tesla2.addModel("Model B", 10000);
        tesla2.addModel("Model C", 120000);
        tesla2.addModel("Model D", 1220000);

        TransportFileWriter transportFileWriter = new StringTransportFileWriter();
        transportFileWriter.setNext(new ColumnTransportFileWriter());

        transportFileWriter.printToFile(tesla);
        transportFileWriter.printToFile(tesla2);

    }
}
