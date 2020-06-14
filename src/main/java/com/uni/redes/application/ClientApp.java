package com.uni.redes.application;

import com.uni.redes.comunication.client.Client;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.run();
    }
}
