package com.uni.redes.client;

import com.uni.redes.KeyboardReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread{
    private KeyboardReader reader;
    private Socket clientSocket;
    private DataOutputStream toServer;
    private BufferedReader fromServer;

    public Client() throws IOException {
        this.reader = new KeyboardReader();
        this.clientSocket = new Socket("127.0.0.1", 6789);
        this.toServer = new DataOutputStream(clientSocket.getOutputStream());
        this.fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run(){
        String frase;
        String fraseModificada;

        try {
            System.out.print("Digite uma frase: ");
            frase = reader.readString();

            if (frase.equals("ESC")) clientSocket.close();

            toServer.writeBytes(frase + '\n');
            fraseModificada = fromServer.readLine();
            System.out.println("Do com.uni.redes.Servidor: " + fraseModificada);
            clientSocket.close();
        }
        catch (Exception e){}
    }
}
