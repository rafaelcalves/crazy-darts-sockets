package com.uni.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardReader {
    private BufferedReader reader;
    public KeyboardReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public double readDouble() throws IOException {
        return Double.parseDouble(reader.readLine());
    }

    public int readInt() throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public String readString() throws IOException {
        return reader.readLine();
    }
}
