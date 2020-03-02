package com.oris.edu.patterns.proxy.server;

import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{

            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());

            double numberOne = in.readDouble();
            double numberTwo = in.readDouble();

            logger.printLog("Two numbers are accepted: %f and %f", numberOne, numberTwo);

            out.writeDouble(numberOne*numberTwo);

            in.close();
            out.close();
            clientSocket.close();
        }catch (Exception e){
            logger.printError(e);
        }
    }
}
