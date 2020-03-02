package com.oris.edu.patterns.proxy.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumbersMultiplierCacheProxy implements NumbersMultiplier{

    private NumbersMultiplier numbersMultiplier;
    private Map<Double, Map<Double, Double>> cache = new HashMap<>();

    public NumbersMultiplierCacheProxy(NumbersMultiplier numbersMultiplier) {
        this.numbersMultiplier = numbersMultiplier;
    }

    @Override
    public double multiplyNumbers(double numberOne, double numberTwo) throws IOException {
        Double result = getFromCache(numberOne, numberTwo);
        if(result == null){
            result = getFromCache(numberTwo, numberOne);
            if(result == null){
                result = numbersMultiplier.multiplyNumbers(numberOne, numberTwo);
                addToCache(result, numberOne, numberTwo);
            }
        }
        return result;
    }

    private void addToCache(double result, double numberOne, double numberTwo) {
        Map<Double, Double> cacheSecondLevel = cache.getOrDefault(numberOne, new HashMap<>());
        cacheSecondLevel.put(numberTwo, result);
        cache.put(numberOne, cacheSecondLevel);
    }

    private Double getFromCache(double firstNumber, double secondNumber){
        if(cache.containsKey(firstNumber)){
            Map<Double, Double> cacheSecondLevel = cache.get(firstNumber);
            if(cacheSecondLevel.containsKey(secondNumber)){
                return cacheSecondLevel.get(secondNumber);
            }
        }

        return null;
    }
}
