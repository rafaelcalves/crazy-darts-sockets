package com.uni.redes.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private List<ClientThread> threads;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(6789);
        this.threads = new ArrayList<>();
    }

    public void run() {

        while(true){
            try {
                ClientThread thread = new ClientThread(serverSocket.accept());
                threads.add(thread);
                thread.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
