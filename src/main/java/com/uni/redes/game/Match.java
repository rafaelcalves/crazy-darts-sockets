package com.uni.redes.game;

import java.util.ArrayList;
import java.util.List;

public class Match {

    public static final int MAX_PLAYERS = 2;
    private List<Player> players;
    private List<Turn> turns;

    public Match(){
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    public Player newPlayer(){
        if(players.size()< MAX_PLAYERS){
            Player player = new Player();
            players.add(player);
            player.setId(players.size() - 1);
            return player;
        }
        return null;
    }

    public void newTurn(int playerId){
        Turn turn = new Turn(playerId);
        turn.newThrow();
        turn.newThrow();
        turn.newThrow();
        turns.add(turn);
        updateScoreBoard(turn.getScore());
    }

    public void updateScoreBoard(Score score){
        Player currentPlayer = players.get(score.getPlayerIndex());
        Score currentPlayerScore = currentPlayer.getScore();

        currentPlayerScore.scorePoints(score.getPoints());
    }

    public boolean hasStarted() {
        return players.size() == 2;
    }
}
