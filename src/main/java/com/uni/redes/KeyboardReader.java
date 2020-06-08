package com.uni.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardReader {
    BufferedReader reader;
    KeyboardReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    double readDouble() throws IOException {
        return Double.parseDouble(reader.readLine());
    }
}
