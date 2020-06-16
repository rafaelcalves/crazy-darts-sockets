package com.uni.redes.comunication.client;

import com.uni.redes.comunication.ConnectionThread;
import com.uni.redes.game.PlayerManager;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends ConnectionThread {

    public ClientConnectionThread(Socket socket, PlayerManager manager) throws IOException {
        super(socket, manager);
    }

    public String run(String command){
        String message = "";
        try {
            PlayerManager manager = (PlayerManager) getManager();
            System.out.print("Próxima ação (" + manager.getNextMove() + "):");
            System.out.println(command);
            message = manager.concatId(command);
            getConnection().writeString(message + '\n');
            message = getConnection().readString();
            System.out.println("Retorno:" + manager.handleMessage(message));
            message = manager.handleMessage(message);
            closeConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
    public String nextMov(){
        PlayerManager manager = (PlayerManager) getManager();
        return "Próxima ação (" + manager.getNextMove() + "):";
    }
    public void closeConnection(){
        try {
            if(getConnection() != null)
                getConnection().close();
        }
        catch (Exception e){
                e.printStackTrace();
            }
    }
}
