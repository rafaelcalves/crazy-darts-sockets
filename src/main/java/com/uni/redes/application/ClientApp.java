package com.uni.redes.application;

import com.uni.redes.client.Client;

public class ClientApp {
    public static void main(String[] args) throws Exception {

        while(true) {
            Client client = new Client();
            client.run();
        }
    }
}
