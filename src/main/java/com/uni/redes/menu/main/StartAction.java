package com.uni.redes.menu.main;

import com.uni.redes.menu.Action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class StartAction implements Action {
    private Socket socketServer;
    private BufferedReader readServer;
    private DataOutputStream sendServer;

    public StartAction() throws IOException {
        socketServer = new Socket("127.0.0.1", 6789);
        sendServer = new DataOutputStream(socketServer.getOutputStream());
        readServer = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
    }

    @Override
    public void execute() throws IOException {

    }
}
