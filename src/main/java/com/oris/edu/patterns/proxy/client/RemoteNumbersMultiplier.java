package com.oris.edu.patterns.proxy.client;

import java.io.IOException;

public class RemoteNumbersMultiplier implements NumbersMultiplier{

    private Client client;

    public RemoteNumbersMultiplier() throws IOException {
        this.client = new Client();
    }

    @Override
    public double multiplyNumbers(double numberOne, double numberTwo) throws IOException {
        client.startConnection("localhost", 7777);
        return client.sendMessage(numberOne, numberTwo);
    }
}
