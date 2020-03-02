package com.oris.edu.patterns.proxy.client;

import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Logger logger = LoggerFactory.getLogger();
    public static void main(String[] args) throws IOException {
        NumbersMultiplier numbersMultiplier = new RemoteNumbersMultiplier();
        numbersMultiplier = new NumbersMultiplierCacheProxy(numbersMultiplier);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("end")) {
            try {
                double[] numbers = Arrays.stream(input.split(" "))
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                System.out.println(numbersMultiplier.multiplyNumbers(numbers[0], numbers[1]));
            } catch (Exception e) {
                logger.printLog("Enter two number separated by a space or 'end' for exit");
            }

        }
    }
}
