package com.uni.redes.comunication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private BufferedReader from;
    private DataOutputStream to;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        init();
    }

    protected void init() throws IOException {
        this.from = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.to = new DataOutputStream(socket.getOutputStream());
    }

    public String readString() throws IOException {
        return from.readLine();
    }

    public void writeString(String string) throws IOException {
        to.writeBytes(string);
    }

    public void close() throws IOException {
        socket.close();
    }

    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    protected void setFrom(BufferedReader from) {
        this.from = from;
    }

    protected void setTo(DataOutputStream to) {
        this.to = to;
    }

    protected Socket getSocket() {
        return socket;
    }
}
