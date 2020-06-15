package com.uni.redes.game;

import com.uni.redes.Constants.Client;
import com.uni.redes.Constants.Server;

public class PlayerManager implements Manager{
    private Integer id;
    private String nextMove;

    public PlayerManager() {
        this.id = -1;
        nextMove = Client.Message.START;
    }

    public String handleMessage(String serverMessage){
        String[] splitMessage = serverMessage.split(";");

        if(splitMessage[0].equals(Server.Message.STARTED)){
            id = Integer.parseInt(splitMessage[1]);
            nextMove = Client.Message.THROW + ";<double>;<double>";
            return Server.Message.STARTED + " " + id;
        } else if (splitMessage[0].equals(Server.Message.THROWED)) {
            return Server.Message.THROWED + " " + splitMessage[1];
        } else if (splitMessage[0].equals(Server.Message.MATCHEND)) {
            return Server.Message.MATCHEND + " " + splitMessage[1];
        } else if (splitMessage[0].equals(Server.Message.Status.FINISHED)) {
            return Server.Message.Status.FINISHED + " " + splitMessage[1];
        } else if (splitMessage[0].equals(Server.Message.GAMEOVER)) {
            nextMove = Client.Message.START;
        }
        return splitMessage[0];
    }

    public String concatId(String string){
        return id + ";" + string;
    }

    public String getNextMove(){
        return this.nextMove;
    }
}
