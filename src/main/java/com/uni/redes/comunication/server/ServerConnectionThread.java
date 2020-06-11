package com.uni.redes.comunication.server;

import com.uni.redes.comunication.ConnectionThread;
import com.uni.redes.game.MatchManager;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {

    public ServerConnectionThread(Socket socket, MatchManager manager) throws IOException {
        super(socket, manager);
    }

    @Override
    public synchronized void start() {
        try {
            String message = getConnection().readString();
            message = getManager().handleMessage(message);
            getConnection().writeString(message);
            getConnection().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
