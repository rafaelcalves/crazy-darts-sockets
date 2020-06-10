package com.uni.redes.comunication.server;

import com.uni.redes.comunication.Connection;

import java.io.IOException;
import java.net.Socket;

public class ServerConnection extends Connection {
    public ServerConnection(Socket socket) throws IOException {
        setSocket(socket);
        init();
    }
}
