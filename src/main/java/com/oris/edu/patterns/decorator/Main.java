package com.oris.edu.patterns.decorator;

import com.oris.edu.patterns.factory_method.*;
import com.oris.edu.patterns.factory_method.exceptions.DuplicateModelNameException;
import com.oris.edu.patterns.factory_method.exceptions.ModelPriceOutOfBoundsException;
import com.oris.edu.patterns.factory_method.exceptions.NoSuchModelNameException;
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

    private static void test(TransportFactory transportFactory) throws Exception {
        log.printLog("Log for " + transportFactory.getClass().getName());

        TransportUtil.setFactory(transportFactory);
        Transport transport = TransportUtil.createInstance("Tesla", 10);
        transport = TransportUtil.synchronizedTransport(transport);
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new TestThread(i, transport));
            thread.start();
        }


    }

    private static class TestThread implements Runnable {
        private int threadNumber;
        private Transport transport;

        public TestThread(int threadNumber, Transport transport) {
            this.threadNumber = threadNumber;
            this.transport = transport;
        }

        @Override
        public void run() {
            log.printLog("Thread %d is started", threadNumber);

            try {
                transport.addModel("Model S"+threadNumber, 2000000);
                transport.addModel("Model X"+threadNumber, 3000000);
                transport.addModel("Model A"+threadNumber, 1000000);
                try {
                    transport.addModel("Model X"+threadNumber, 3000000);
                } catch (DuplicateModelNameException e) {
                    log.printError(e);
                }

                transport.removeModel("Model S"+threadNumber);

                try {

                    transport.changeModelName("Model X"+threadNumber, "Model A"+threadNumber);
                } catch (DuplicateModelNameException e) {
                    log.printError(e);
                }

                try {
                    transport.changeModelPrice("Model A"+threadNumber, -500);
                } catch (ModelPriceOutOfBoundsException e) {
                    log.printError(e);
                }

                transport.changeModelPrice("Model A"+threadNumber, 500);
            } catch (DuplicateModelNameException | NoSuchModelNameException e) {
                log.printLog("Thread %d is not finished", threadNumber);
            }
            log.printLog("Thread %d is finished", threadNumber);
        }
    }
}
