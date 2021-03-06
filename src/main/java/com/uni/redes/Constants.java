package com.uni.redes;

public interface Constants {
    interface Client {
        String IP = "127.0.0.1";
        int PORT = 6789;
        interface Message {
            String START = "START";
            String THROW = "THROW";
            String ESC = "ESC";
            String STATUS = "STATUS";
            String ENDTURN = "ENDTURN";
        }
    }
    interface Server {
        interface Message {
            String STARTED = "STARTED";
            String GAMEOVER = "GAMEOVER";
            String THROWED = "THROWED";
            String MATCHEND = "MATCHEND";
            interface Error {
                String MATCH = "MATCHERROR";
                String TURN = "TURNERROR";
                String THROW = "THROWERROR";
                String EMPTY = "EMPTYERROR";
                String ANY = "ERROR";
            }
            interface Status {
                String NOMATCH = "NOMATCH";
                String WAIT = "WAIT";
                String YOURTURN = "YOURTURN";
                String NOTYOURTURN = "NOTYOURTURN";
                String FINISHED = "FINISHED";
            }
        }
    }
}
