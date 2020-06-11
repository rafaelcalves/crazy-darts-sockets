package com.uni.redes.comunication.client;

import com.uni.redes.Constants;
import com.uni.redes.game.PlayerManager;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private PlayerManager manager;

    public Client() {
        this.manager = new PlayerManager();
    }

    public void run() {
        while(true) {
            try {
                ClientConnectionThread connectionThread = new ClientConnectionThread(new Socket(Constants.Client.IP,Constants.Client.PORT), manager);
                connectionThread.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
