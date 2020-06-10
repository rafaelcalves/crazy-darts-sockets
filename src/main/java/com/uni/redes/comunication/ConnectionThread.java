package com.uni.redes.comunication;

public abstract class ConnectionThread extends Thread{
    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }
}
