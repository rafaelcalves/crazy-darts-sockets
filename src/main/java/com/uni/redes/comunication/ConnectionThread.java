package com.uni.redes.comunication;

import com.uni.redes.game.Manager;

import java.io.IOException;
import java.net.Socket;

public abstract class ConnectionThread extends Thread{
    private Manager manager;
    private Connection connection;

    public ConnectionThread(Socket socket, Manager manager) throws IOException {
        this.connection = new Connection(socket);
        this.manager = manager;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected Manager getManager() {
        return manager;
    }
}
