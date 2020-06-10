package com.uni.redes.comunication.client;

import com.uni.redes.comunication.Connection;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Connection {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 6789;

    public ClientConnection() throws IOException {
        setSocket(new Socket(IP, PORT));
        init();
    }
}
