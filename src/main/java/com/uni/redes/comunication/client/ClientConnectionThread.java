package com.uni.redes.comunication.client;

import com.uni.redes.KeyboardReader;
import com.uni.redes.comunication.ConnectionThread;
import com.uni.redes.game.Player;
import com.uni.redes.game.PlayerManager;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {
    private KeyboardReader reader;

    public ClientConnectionThread(Socket socket, PlayerManager manager) throws IOException {
        super(socket, manager);
        reader = new KeyboardReader();
    }

    public void run(){
        try {
            PlayerManager manager = (PlayerManager) getManager();
            System.out.print("Próxima ação (" + manager.getNextMove() + "):");
            String message = manager.concatId(reader.readString());

            getConnection().writeString(message + '\n');
            message = getConnection().readString();
            System.out.println(manager.handleMessage(message));
            getConnection().close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
