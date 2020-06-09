package com.uni.redes.menu;

import com.uni.redes.KeyboardReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    private String title;
    private List<Option> options;
    private KeyboardReader inputReader;
    public Menu(){
        options = new ArrayList<>();
        inputReader = new KeyboardReader();
    }
    public void readOption() throws IOException {
        int option = this.inputReader.readInt();
       // options.get(option).getAction().execute();
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    private String optionsToString(){
        String result = "";
        for (int i = 0; i < options.size(); i++) {
            result += i + " -> " + options.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return title + "\n\n" + optionsToString();
    }
}
