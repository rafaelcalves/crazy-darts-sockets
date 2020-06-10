package com.uni.redes.comunication.server;

import com.uni.redes.comunication.ConnectionThread;
import com.uni.redes.game.MatchManager;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {
    private MatchManager manager;

    public ServerConnectionThread(Socket socket, MatchManager manager) throws IOException {
        setConnection(new ServerConnection(socket));
        this.manager = manager;
    }

    @Override
    public synchronized void start() {
        try {
            String message = getConnection().readString();
            message = manager.handleAction(message);
            getConnection().writeString(message);
            getConnection().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
