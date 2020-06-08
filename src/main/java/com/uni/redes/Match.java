package com.uni.redes;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private List<Player> players;
    private List<Turn> turns;

    Match(){
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    Player newPlayer(){
        Player player = new Player();
        players.add(player);
        player.setId(players.size() - 1);
        return player;
    }

    void newTurn(int playerId){
        Turn turn = new Turn(playerId);
        turn.newThrow();
        turn.newThrow();
        turn.newThrow();
        turns.add(turn);
        updateScoreBoard(turn.getScore());
    }

    void updateScoreBoard(Score score){
        Player currentPlayer = players.get(score.getPlayerIndex());
        Score currentPlayerScore = currentPlayer.getScore();

        currentPlayerScore.scorePoints(score.getPoints());
    }
}
