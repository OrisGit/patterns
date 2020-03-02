package com.oris.edu.patterns.proxy.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        if(clientSocket!=null){
            stopConnection();
        }
        clientSocket = new Socket(ip, port);
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
    }

    public double sendMessage(double numberOne, double numberTwo) throws IOException {
        out.writeDouble(numberOne);
        out.writeDouble(numberTwo);
        return in.readDouble();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        clientSocket = null;
    }
}
