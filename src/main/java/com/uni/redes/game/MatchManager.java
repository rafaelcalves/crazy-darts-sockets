package com.uni.redes.game;

public class MatchManager {
    private Match match;

    public String handleAction(String clientMessage){
        if(clientMessage.equals("START"))  {
            if(match == null) {
                match = new Match();
                match.newPlayer();
                return "WAIT";
            }else if(match.hasStarted()) {
                return "ERROR";
            }else{
                match.newPlayer();
                return "STARTED";
            }
        } else if (clientMessage.equals("ESC")){
            match = null;
            return "GAMEOVER";
        }
        return "ERROR";
    }
}
