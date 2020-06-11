package com.uni.redes.game;

public class MatchManager implements Manager {
    private Match match;
    private Turn currentTurn;

    public String handleMessage(String clientMessage){
        String[] splitMessage  = clientMessage.split(";");
        if(splitMessage.length == 1) return "EMPTY";
        if(splitMessage[1].equals("START")) {
            if (match == null) {
                match = new Match();
                int id = match.newPlayer();
                return "STARTED;" + id;
            } else if (match.hasStarted() || hasId(splitMessage[0])) {
                return "MATCHERROR";
            } else {
                int id = match.newPlayer();
                if(id >= 0) {
                    currentTurn = match.newTurn();
                    return "STARTED;" + id;
                }
            }
        } else if (splitMessage[1].equals("THROW")){
            if(match.hasFinished()) return "MATCHEND;" + match.getWinnerIndex();
            if (isExpectedPlayer(splitMessage[0])){
                double x;
                double y;
                try{
                    x = Double.parseDouble(splitMessage[2]);
                    y = Double.parseDouble(splitMessage[3]);
                } catch (Exception e){
                    e.printStackTrace();
                    return "THROWERROR";
                }
                int scoredPoints = match.newThrow(x, y);
                if(scoredPoints < 0 || currentTurn.hasFinished()) currentTurn = match.getCurrentTurn();
                return "THROWED;" + scoredPoints;
            }
            return "TURNERROR";
        } else if (splitMessage[1].equals("ESC")){
            match = null;
            return "GAMEOVER";
        }
        return "ERROR";
    }

    private boolean hasId(String stringId) {
        return Integer.parseInt(stringId) >= 0;
    }

    private boolean isExpectedPlayer(String playerId) {
        return currentTurn != null && playerId.equals(String.valueOf(currentTurn.getPlayerIndex()));
    }
}
