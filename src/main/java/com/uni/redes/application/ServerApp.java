package com.uni.redes.application;

import com.uni.redes.comunication.server.Server;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.run();
    }
}
