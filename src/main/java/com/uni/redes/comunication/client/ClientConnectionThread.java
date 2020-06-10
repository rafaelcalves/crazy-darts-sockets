package com.uni.redes.comunication.client;

import com.uni.redes.KeyboardReader;
import com.uni.redes.comunication.ConnectionThread;

import java.io.IOException;

public class ClientConnectionThread extends ConnectionThread {
    private KeyboardReader reader;

    public ClientConnectionThread() throws IOException {
        this.reader = new KeyboardReader();
        setConnection(new ClientConnection());
    }

    public void run(){
        try {
            System.out.print("Digite uma frase: ");
            String message = reader.readString();

            getConnection().writeString(message + '\n');
            message = getConnection().readString();
            System.out.println("Do Servidor: " + message);
            getConnection().close();
        }
        catch (Exception e){}
    }
}
