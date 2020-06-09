package com.uni.redes;

import com.uni.redes.game.Match;
import com.uni.redes.game.Player;

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
