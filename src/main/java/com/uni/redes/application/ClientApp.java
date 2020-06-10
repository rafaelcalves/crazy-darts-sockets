package com.uni.redes.application;

import com.uni.redes.comunication.client.ClientConnectionThread;

public class ClientApp {
    public static void main(String[] args) throws Exception {

        while(true) {
            ClientConnectionThread clientConnectionThread = new ClientConnectionThread();
            clientConnectionThread.run();
        }
    }
}
