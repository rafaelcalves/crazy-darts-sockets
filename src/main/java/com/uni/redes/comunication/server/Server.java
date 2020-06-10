package com.uni.redes.comunication.server;

import com.uni.redes.game.MatchManager;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;
    private MatchManager manager;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(6789);
        this.manager = new MatchManager();
    }

    public void run() {

        while(true){
            try {
                ServerConnectionThread thread = new ServerConnectionThread(serverSocket.accept(), manager);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
