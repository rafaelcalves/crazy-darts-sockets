package com.uni.redes.game;

public class PlayerManager implements Manager{
    private Integer id;
    private String nextMove;

    public PlayerManager() {
        this.id = -1;
        nextMove = "START";
    }

    public String handleMessage(String serverMessage){
        String[] splitMessage = serverMessage.split(";");

        if(splitMessage[0].equals("STARTED")){
            id = Integer.parseInt(splitMessage[1]);
            nextMove = "THROW;<double>;<double>";
            return "PLAYER " + id;
        } else if (splitMessage[0].equals("THROWED")) {
            return "THROWED " + splitMessage[1];
        } else if (splitMessage[0].equals("THROWERROR")) {
            return "THROW ERROR";
        } else if (splitMessage[0].equals("TURNERROR")) {
            return "NOT YOUR TURN";
        } else if (splitMessage[0].equals("MATCHERROR")) {
            return "FULL MATCH";
        } else if (splitMessage[0].equals("MATCHEND")) {
            return "WINNER " + splitMessage[1];
        } else if (splitMessage[0].equals("GAMEOVER")) {
            nextMove = "START";
            return "GAME OVER";

        } else if (splitMessage[0].equals("EMPTY")) {
            return "EMPTY COMMAND";
        }
        return "UNKNOWN ERROR";
    }

    public String concatId(String string){
        return id + ";" + string;
    }

    public String getNextMove(){
        return this.nextMove;
    }
}
