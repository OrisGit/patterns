package com.oris.edu.patterns.prototype;

import com.oris.edu.patterns.factory_method.*;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;
import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

public class Main {

    static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) throws Exception {
        TransportFactory[] factories = new TransportFactory[]{new CarFactory(), new MotorcycleFactory()};
        for (TransportFactory factory : factories) {
            test(factory);
        }
    }

    public static void test(TransportFactory factory) throws DuplicateModelNameException, NoSuchModelNameException {
        ModelsStatisticCalculator.setFactory(factory);
        Transport original = ModelsStatisticCalculator.createInstance("Tesla", 10);
        original.addModel("Model S", 2000000);
        original.addModel("Model X", 3000000);
        original.addModel("Model A", 1000000);

        Transport clone = original.clone();

        logger.printLog(original.toString());
        logger.printLog(clone.toString());

        clone.changeModelName("Model A", "Model Y");

        logger.printLog(original.toString());
        logger.printLog(clone.toString());

    }
}
