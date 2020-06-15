package com.uni.redes.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.uni.redes.Constants;
import com.uni.redes.comunication.client.ClientConnectionThread;
import com.uni.redes.game.PlayerManager;

public class UIApp extends JFrame {
    boolean start, esc;
    String thrown;
    private PlayerManager manager;
    private ClientConnectionThread connectionThread;

    JPanel windowPanel = new JPanel();
    JPanel homePanel = new JPanel();
    JPanel instPanel = new JPanel();
    JPanel aboutPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton instButton = new JButton("Instruction");
    JButton aboutButton = new JButton("About");
    JButton newCoordButton = new JButton("New coordinates");
    JButton back1Button = new JButton("Back");
    JButton back2Button = new JButton("Back");
    JButton throwButton = new JButton("Throw");
    CardLayout cardlayout = new CardLayout();

    JLabel instText = new JLabel("<html>Welcome to Crazy Darts Sockets. <br>" +
            "<br>" +
            "Two players throw their darts by entering x and y, each player has three moves.<br>" +
            "Blablabla </html>");

    JLabel aboutText = new JLabel("<html>This is a computer network work and it was developed by Fábio Krein and Rafael Côrrea. Blablabla</html>");

    double xCoord = 0.0;
    double yCoord = 0.0;
    JLabel xText = new JLabel("<html>X: " + xCoord + "</html>");
    JLabel yText = new JLabel("<html>Y: " + yCoord + "</html>");

    JLabel turn1 = new JLabel("<html>Turno 1:</html>");
    JLabel thrown1 = new JLabel("<html>1)</html>");
    JLabel thrown2 = new JLabel("<html>2)</html>");
    JLabel thrown3 = new JLabel("<html>3)</html>");

    JLabel turn2 = new JLabel("<html>Turno 2:</html>");
    JLabel thrown4 = new JLabel("<html>4)</html>");
    JLabel thrown5 = new JLabel("<html>5)</html>");
    JLabel thrown6 = new JLabel("<html>6)</html>");

    JLabel turn3 = new JLabel("<html>Turno 3:</html>");
    JLabel thrown7 = new JLabel("<html>7)</html>");
    JLabel thrown8 = new JLabel("<html>8)</html>");
    JLabel thrown9 = new JLabel("<html>9)</html>");

    JLabel messages = new JLabel("<html>Messages:</html>");

    public UIApp()  {
        this.manager = new PlayerManager();
    }

    public void run() {
        try {
            connectionThread = new ClientConnectionThread(new Socket(Constants.Client.IP,Constants.Client.PORT), manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Window UI = new Window();
        Init();
        UI.add(windowPanel);
        UI.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
        UI.setVisible(true);
    }
    public void newConnection(){
        try {
            connectionThread = new ClientConnectionThread(new Socket(Constants.Client.IP,Constants.Client.PORT), manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Init() {

        windowPanel.setLayout(cardlayout);

        homePanel.setLayout(null);

        startButton.setBounds(120,500, 100,50);
        instButton.setBounds(240,500, 120,50);
        aboutButton.setBounds(380,500, 100,50);

        JLabel label = new JLabel();
        ImageIcon dbMenu = new ImageIcon("res/images/db_menu.png");
        Image dbImg = dbMenu.getImage();
        Image newImg = dbImg.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(newImg));
        label.setBounds(100,50, 400,400);
        homePanel.add(label);

        homePanel.add(startButton);
        homePanel.add(instButton);
        homePanel.add(aboutButton);


        gamePanel.setLayout(null);
        throwButton.setBounds(150,500, 120,50);
        newCoordButton.setBounds(300,500, 150,50);

        JLabel label1 = new JLabel();
        ImageIcon dbGame = new ImageIcon("res/images/db_game.png");
        Image dbImg1 = dbGame.getImage();
        Image newImg1 = dbImg1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        label1.setIcon(new ImageIcon(newImg1));
        label1.setBounds(150,50, 300,300);
        gamePanel.add(label1);

        turn1.setBounds(20,50,100,20);
        turn1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(turn1);
        thrown1.setBounds(20,70,100,20);
        thrown1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown1);
        thrown2.setBounds(20,90,100,20);
        thrown2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown2);
        thrown3.setBounds(20,110,100,20);
        thrown3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown3);

        turn2.setBounds(20,140,100,20);
        turn2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(turn2);
        thrown4.setBounds(20,160,100,20);
        thrown4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown4);
        thrown5.setBounds(20,180,100,20);
        thrown5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown5);
        thrown6.setBounds(20,200,100,20);
        thrown6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown6);

        turn3.setBounds(20,230,100,20);
        turn3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(turn3);
        thrown7.setBounds(20,250,100,20);
        thrown7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown7);
        thrown8.setBounds(20,270,100,20);
        thrown8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown8);
        thrown9.setBounds(20,290,100,20);
        thrown9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(thrown9);

        messages.setBounds(20,320,100,20);
        messages.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(messages);

        xText.setBounds(280,405,600,20);
        xText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(xText);
        yText.setBounds(280,435,600,20);
        yText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(yText);
        gamePanel.add(newCoordButton);
        gamePanel.add(throwButton);


        instPanel.setLayout(null);
        back1Button.setBounds(240,500, 120,50);

        instText.setBounds(10,5,600,100);
        instPanel.add(instText);
        instPanel.add(back1Button);


        aboutPanel.setLayout(null);
        back2Button.setBounds(240,500, 120,50);

        aboutText.setBounds(10,5,600,50);
        aboutPanel.add(aboutText);
        aboutPanel.add(back2Button);


        windowPanel.add(homePanel,"1");
        windowPanel.add(gamePanel,"2");
        windowPanel.add(instPanel,"3");
        windowPanel.add(aboutPanel,"4");

        cardlayout.show(windowPanel, "1");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String returned = "";
                returned = connectionThread.run("START\n");
                System.out.println(returned); //retirar
                if(returned.equals("STARTED 0") || returned.equals("STARTED 1")) {
                    System.out.println(connectionThread.nextMov()); //retirar
                    updateMessage(gamePanel,"Messages: <br>First turn");
                    updateCoords();
                    cardlayout.show(windowPanel, "2");
                }
                else if(returned.equals("MATCHERROR")) {
                    updateMessage(homePanel, "Server is full, please wait.");
                    cardlayout.show(windowPanel, "1");
                }
                else {
                    updateMessage(homePanel,"Error, please try again.");
                    cardlayout.show(windowPanel, "1");
                }
            }
        });

        instButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(windowPanel, "3");
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(windowPanel, "4");
            }
        });

        newCoordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCoords();
            }
        });

        throwButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(connectionThread.nextMov()); //retirar
//                newConnection();
//                String returned = "";
//                connectionThread.run("THROW;" + truncate(xCoord) + ";" + truncate(yCoord) + "\n");
//                System.out.println(returned); //retirar
//                if(returned.equals("THROWERROR")) {
//                    updateMessage(gamePanel,"Messages: <br>Error, please try again");
//                }
//                else if(returned.equals("ERROR")) {
//                    updateMessage(gamePanel,"Messages: <br>Error, please try again.");
//                }
//                else {
////                    System.out.println(connectionThread.nextMov()); //retirar
////                    updateMessage(gamePanel, "Second turn");
////                    updateCoords();
//                }
            }
        });

        back1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(windowPanel, "1");
            }
        });

        back2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(windowPanel, "1");
            }
        });
    }

    public static String truncate(double value) {
        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
        return df.format(value);
    }

    public void update() {
        windowPanel.revalidate();
        windowPanel.repaint();
    }

    public void updateCoords() {
        gamePanel.remove(xText);
        gamePanel.remove(yText);
        xCoord = Math.random() * ((10 - 1) + 1);
        yCoord = Math.random() * ((10 - 1) + 1);
        xText = new JLabel("<html>X: " + truncate(xCoord) + "</html>");
        yText = new JLabel("<html>Y: " + truncate(yCoord) + "</html>");
        xText.setBounds(280,405,600,20);
        xText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(xText);
        yText.setBounds(280,435,600,20);
        yText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(yText);
        update();
    }

    public void updateThrown(JLabel newThrown, String points) {
        int y = newThrown.getY();
        gamePanel.remove(newThrown);
        newThrown = new JLabel("<html>" + points + "</html>");
        newThrown.setBounds(20,y,100,20);
        newThrown.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        gamePanel.add(newThrown);
        update();
    }

    public void updateMessage(JPanel panelTemp, String messageNew) {
        panelTemp.remove(messages);
        messages = new JLabel("<html>" + messageNew + "</html>");
        messages.setBounds(20,320,100,100);
        messages.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        panelTemp.add(messages);
        update();
    }
}
