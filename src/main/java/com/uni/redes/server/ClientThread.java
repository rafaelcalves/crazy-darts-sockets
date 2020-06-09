package com.uni.redes.server;

import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    protected ServerConnection connection;

    public ClientThread(Socket socket) throws IOException {
        connection = new ServerConnection(socket);
    }

    public void run(){

        String fraseCliente;
        String fraseMaiusculas;

        try {
            fraseCliente = connection.readString();
            fraseMaiusculas = fraseCliente.toUpperCase() + '\n';
            connection.writeString(fraseMaiusculas);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
