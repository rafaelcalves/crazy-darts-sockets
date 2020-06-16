package com.uni.redes.application;

import com.uni.redes.UI.UIApp;
import com.uni.redes.game.PlayerManager;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        UIApp app = new UIApp();
        app.create();
        while (true){
            try {
                app.run();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
