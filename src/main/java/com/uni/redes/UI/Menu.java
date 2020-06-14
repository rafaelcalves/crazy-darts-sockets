package com.uni.redes.UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{

    public Menu() {


//        Window window = new Window();
//        window.setVisible(true);


//        JButton start = new JButton("Start");
//
//        window.add(start);
//            start.addActionListener(this);
//
//        JButton instructions = new JButton("Instructions");
//
//        window.add(instructions);
//            instructions.addActionListener(this);
//
//        JButton about = new JButton("About");
//
//        window.add(about);
//            about.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String name = e.getActionCommand();

        if(name.equals("Start")){

        }
        else if(name.equals("Instructions")){
            System.out.println("\nWelcome to Crazy Darts Sockets.\n\nTwo players throw their darts by entering x and y, each player has three moves." +
                    " A player 1 move and then a player 2 move. Blablabla\n");
        }
        else if(name.equals("About")) {
            System.out.println("\nThis is a computer network work and it was developed by Fábio Krein and Rafael Côrrea. Blablabla\n");
        }
    }
}
