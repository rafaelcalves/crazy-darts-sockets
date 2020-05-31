package com.uni.redes.server;

import com.uni.redes.GameManager;

import java.util.ArrayList;
import java.util.List;

public class ServerGameManager extends GameManager {

    public ServerGameManager() {
        this.players = new ArrayList<>();
    }

    ServerPlayer newPlayer(){
        ServerPlayer serverPlayer = new ServerPlayer();
        players.add(serverPlayer);
        serverPlayer.setId(players.size());
        return serverPlayer;
    }
}
