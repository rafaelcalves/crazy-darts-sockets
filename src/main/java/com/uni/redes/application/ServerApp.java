package com.uni.redes.application;

import com.uni.redes.comunication.server.Server;

import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();
    }
}
