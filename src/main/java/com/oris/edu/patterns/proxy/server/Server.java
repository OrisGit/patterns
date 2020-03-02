package com.oris.edu.patterns.proxy.server;

import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private Logger logger = LoggerFactory.getLogger(Server.class);
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        if(serverSocket!=null){
            stop();
        }
        serverSocket = new ServerSocket(port);
        logger.printLog("The server is running on port %d", port);
        while(true)
            new ClientHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        logger.printLog("The server is stopped");
        serverSocket.close();
        serverSocket = null;
    }
}
