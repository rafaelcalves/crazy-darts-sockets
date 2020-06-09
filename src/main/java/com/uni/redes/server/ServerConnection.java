package com.uni.redes.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection {
    private Socket socketConnection;
    private BufferedReader fromClient;
    private DataOutputStream toClient;

    public ServerConnection(Socket socket) throws IOException {
        socketConnection = socket;
        fromClient = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
        toClient =  new DataOutputStream(socketConnection.getOutputStream());
    }

    public String readString() throws IOException {
        return fromClient.readLine();
    }

    public void writeString(String string) throws IOException {
        toClient.writeBytes(string);
    }
}
