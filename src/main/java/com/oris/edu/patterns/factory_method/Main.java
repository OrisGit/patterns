package com.oris.edu.patterns.factory_method;

import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.ModelPriceOutOfBoundsException;
import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

public class Main {
    private static Logger log = LoggerFactory.getLogger();

    public static void main(String[] args) throws Exception {
        TransportFactory[] factories = new TransportFactory[]{new CarFactory(), new MotorcycleFactory()};
        for (TransportFactory factory : factories) {
            test(factory);
        }
    }

    private static void test(TransportFactory transportFactory) throws Exception{
        log.printLog("Log for "+transportFactory.getClass().getName());

        ModelsStatisticCalculator.setFactory(transportFactory);
        Transport transport = ModelsStatisticCalculator.createInstance("Tesla", 10);
        transport.addModel("Model S", 2000000);
        transport.addModel("Model X", 3000000);
        transport.addModel("Model A", 1000000);
        try{
            transport.addModel("Model X", 3000000);
        }catch (DuplicateModelNameException e){
            log.printError(e);
        }


        ModelsStatisticCalculator.printAllModels(transport);

        transport.removeModel("Model S");

        ModelsStatisticCalculator.printAllModels(transport);

        try{

            transport.changeModelName("Model X", "Model A");
        }catch (DuplicateModelNameException e){
            log.printError(e);
        }

        ModelsStatisticCalculator.printAllModels(transport);

        ModelsStatisticCalculator.printAllPrices(transport);

        try {
            transport.changeModelPrice("Model A", -500);
        }catch (ModelPriceOutOfBoundsException e){
            log.printError(e);
        }

        transport.changeModelPrice("Model A", 500);
        ModelsStatisticCalculator.printAllPrices(transport);
    }
}
