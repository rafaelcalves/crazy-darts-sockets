package com.uni.redes.game;

import java.util.ArrayList;
import java.util.List;

public class Match {

    public static final int MAX_PLAYERS = 2;
    public static final int MAX_TURNS = 6;
    private List<Player> players;
    private List<Turn> turns;

    public Match() {
        players = new ArrayList<>();
        turns = new ArrayList<>();
    }

    public int newPlayer() {
        if (hasStarted()) return -1;

        Player player = new Player();
        players.add(player);
        player.setId(players.size() - 1);
        return player.getId();
    }

    public Turn newTurn() {
        if(hasFinished()) return null;
        Turn turn = new Turn(getNextPlayer());
        turns.add(turn);
        return turn;
    }

    public int newThrow(double x, double y) {
        Turn turn = getCurrentTurn();
        if (turn == null) return -1;
        return doThrow(turn, x, y);
    }

    private int doThrow(Turn turn, double x, double y) {
        int scoredPoints = turn.newThrow(x, y);
        if (scoredPoints < 0) endTurn();
        return scoredPoints;
    }

    private void endTurn(){
        updateScoreBoard();
        newTurn();
    }

    public Turn getCurrentTurn() {
        if (turns.isEmpty()) return null;
        return turns.get(turns.size() - 1);
    }

    private int getNextPlayer() {
        if (!turns.isEmpty() && turns.get(turns.size() - 1).getPlayerIndex() == 1) {
            return 0;
        }
        return 1;
    }

    public void updateScoreBoard() {
        Turn currentTurn = getCurrentTurn();
        if(currentTurn == null) return;
        Score currentTurnScore = currentTurn.getScore();
        Player currentPlayer = players.get(currentTurn.getPlayerIndex());
        Score currentPlayerScore = currentPlayer.getScore();
        currentPlayerScore.scorePoints(currentTurnScore.getPoints());
    }

    public boolean hasStarted() {
        return players.size() == MAX_PLAYERS;
    }

    public boolean hasFinished(){
        return turns.size() == MAX_TURNS;
    }

    public int getWinnerIndex(){
        int player0Points = players.get(0).getScore().getPoints();
        int player1Points = players.get(1).getScore().getPoints();
        if(player0Points == player1Points) return -1;
        if(player0Points > player1Points) return 0;
        return 1;
    }
}
