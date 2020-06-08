package com.uni.redes;

public class LocalGame {
    public static void main(String argv[]) throws Exception {
        Match match = new Match();
        Player player1 = match.newPlayer();
        Player player2 = match.newPlayer();

        match.newTurn(player1.getId());
        match.newTurn(player2.getId());
        System.out.printf("");


    }
}
