package com.uni.redes.game;

import com.uni.redes.Constants.Client;
import com.uni.redes.Constants.Server;

public class MatchManager implements Manager {
    private Match match;
    private Turn currentTurn;

    public String handleMessage(String clientMessage){
        String[] splitMessage  = clientMessage.split(";");
        if(splitMessage.length == 1) return Server.Message.Error.EMPTY;
        if(splitMessage[1].equals(Client.Message.START)) {
            if (match == null) {
                match = new Match();
                int id = match.newPlayer();
                return Server.Message.STARTED + ";" + id;
            } else if (match.hasStarted() || hasId(splitMessage[0])) {
                return Server.Message.Error.MATCH;
            } else {
                int id = match.newPlayer();
                if(id >= 0) {
                    currentTurn = match.newTurn();
                    return Server.Message.STARTED + ";" + id;
                }
            }
        } else if (splitMessage[1].equals(Client.Message.THROW)){
            if(match.hasFinished()) return Server.Message.MATCHEND + ";" + match.getWinnerIndex();
            if (isExpectedPlayer(splitMessage[0])){
                double x;
                double y;
                try{
                    x = Double.parseDouble(splitMessage[2]);
                    y = Double.parseDouble(splitMessage[3]);
                } catch (Exception e){
                    e.printStackTrace();
                    return Server.Message.Error.THROW;
                }
                int scoredPoints = match.newThrow(x, y);
                if(scoredPoints < 0 || currentTurn.hasFinished()) currentTurn = match.getCurrentTurn();
                return Server.Message.THROWED + ";" + scoredPoints;
            }
            return Server.Message.Error.TURN;
        } else if (splitMessage[1].equals(Client.Message.ESC)){
            match = null;
            return Server.Message.GAMEOVER;
        }
        return Server.Message.Error.ANY;
    }

    private boolean hasId(String stringId) {
        return Integer.parseInt(stringId) >= 0;
    }

    private boolean isExpectedPlayer(String playerId) {
        return currentTurn != null && playerId.equals(String.valueOf(currentTurn.getPlayerIndex()));
    }
}
