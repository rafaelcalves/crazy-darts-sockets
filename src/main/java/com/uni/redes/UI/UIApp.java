package com.uni.redes.UI;

import com.uni.redes.Constants;
import com.uni.redes.comunication.client.ClientConnectionThread;
import com.uni.redes.game.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UIApp extends JFrame {
    boolean connected = false, start = false, end = false, myTurn = false, win = false;
    private final PlayerManager manager;
    private ClientConnectionThread connectionThread;
    int move = 0, id = 0;

    JPanel windowPanel = new JPanel();
    JPanel homePanel = new JPanel();
    JPanel instPanel = new JPanel();
    JPanel aboutPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel winPanel = new JPanel();
    JPanel losePanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton instButton = new JButton("Instruction");
    JButton aboutButton = new JButton("About");
    JButton newCoordButton = new JButton("New coordinates");
    JButton throwButton = new JButton("Throw");
    JButton back1Button = new JButton("Back");
    JButton back2Button = new JButton("Back");
    JButton resultButton = new JButton("View result");
    JButton menuButton = new JButton("Menu");
    JButton menu1Button = new JButton("Menu");
    CardLayout cardlayout = new CardLayout();

    JLabel instText = new JLabel("<html>Welcome to Crazy Darts Sockets. <br>" +
            "<br>" +
            "Two players throw their darts by entering x and y, each player has nine moves.<br>" +
            "There are three moves per turn.<br>" +
            "Whoever scores the most points wins.<br>" +
            "Good luck!  </html>");

    JLabel aboutText = new JLabel("<html>This is a computer network work for the class of professor Marcio Martins.<br>" +
            "It was developed by Fábio Krein and Rafael Côrrea. </html>");

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

    JLabel[] plays = new JLabel[]{thrown1,thrown2,thrown3,thrown4,thrown5,thrown6,thrown7,thrown8,thrown9};
    JLabel messages = new JLabel("<html>Messages:</html>");

    JLabel winText = new JLabel("<html>YOU WON!</html>");
    JLabel loseText = new JLabel("<html>You lose :(</html>");

    public UIApp()  {
        this.manager = new PlayerManager();
    }

    public void run() throws InterruptedException {
        Thread.sleep(1000);
        if(connected)
            updateStatus();
    }

    public void create() {
        Window UI = new Window();
        Init();
        UI.add(windowPanel);
        UI.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
        UI.setVisible(true);
    }

    public void newConnection(){
        if(connectionThread != null) {
            connectionThread.closeConnection();
        }
        try {
            connectionThread = new ClientConnectionThread(new Socket(Constants.Client.IP,Constants.Client.PORT), manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Init() {

        windowPanel.setLayout(cardlayout);

        reset();

        windowPanel.add(homePanel,"1");
        windowPanel.add(gamePanel,"2");
        windowPanel.add(instPanel,"3");
        windowPanel.add(aboutPanel,"4");
        windowPanel.add(winPanel,"5");
        windowPanel.add(losePanel,"6");

        cardlayout.show(windowPanel, "1");

        startButton.addActionListener(e -> {
            String returned;
            newConnection();
            returned = connectionThread.run("START\n");
//            System.out.println(returned); //retirar
            if(returned.equals("STARTED 0") || returned.equals("STARTED 1")) {
                if(returned.equals("STARTED 0"))
                    id = 0;
                else
                    id = 1;

                connected = true;
//                System.out.println("Connected"); //retirar
                updateStatus();
//                System.out.println(connectionThread.nextMov()); //retirar
                if(myTurn){
                    updateMessage(gamePanel,"Messages: <br>Your turn<br>Move:" + (move + 1));
                }
                else{
                    updateMessage(gamePanel,"Messages: <br>Waiting other player");
                }
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
        });

        instButton.addActionListener(e -> cardlayout.show(windowPanel, "3"));

        aboutButton.addActionListener(e -> cardlayout.show(windowPanel, "4"));

        newCoordButton.addActionListener(e -> updateCoords());

        throwButton.addActionListener(e -> {
//            System.out.println(connectionThread.nextMov());
            if(myTurn && move < 9){
                newConnection();
                String returned;
                returned = connectionThread.run("THROW;" + truncate(xCoord) + ";" + truncate(yCoord) + "\n");
//                System.out.println(returned); //retirar
                if(returned.equals("THROWERROR")) {
                    updateMessage(gamePanel,"Messages: <br>Error, please try again");
                }
                else if(returned.equals("ERROR")) {
                    updateMessage(gamePanel,"Messages: <br>Error, please try again.");
                }
                else {
                    if(returned.length() == 9)
                        updateThrown(move, (move + 1) +") "+ returned.charAt(8));
                    else
                        updateThrown(move, (move + 1) +") "+ returned.charAt(8) + returned.charAt(9));

//                    System.out.println(connectionThread.nextMov()); //retirar
                    updateMessage(gamePanel, "Messages: <br>Your turn<br>Move:" + (move + 1));
                    updateCoords();
//                    System.out.println(Integer.toString(move)); //retirar
                    if(move == 2 || move == 5 || move == 8) {
//                        newConnection();
//                        connectionThread.run("THROW;" + truncate(xCoord) + ";" + truncate(yCoord) + "\n");
                        updateStatus();
                    }
                    ++move;
                }
            }
        });

        back1Button.addActionListener(e -> cardlayout.show(windowPanel, "1"));

        back2Button.addActionListener(e -> cardlayout.show(windowPanel, "1"));

        menuButton.addActionListener(e -> {
            newConnection();
            String returned;
            returned = connectionThread.run("ESC\n");
//                System.out.println(returned); //retirar
            if(returned.equals("ERROR")) {
                updateMessage(winPanel,"Messages: <br>Error, please try again.");
            }
            else {
                remove();
                reset();
                cardlayout.show(windowPanel, "1");
            }

        });
        menu1Button.addActionListener(e -> {

            newConnection();
            String returned;
            returned = connectionThread.run("ESC\n");
//                System.out.println(returned); //retirar
            if(returned.equals("ERROR")) {
                updateMessage(winPanel,"Messages: <br>Error, please try again.");
            }
            else {
                remove();
                reset();
                cardlayout.show(windowPanel, "1");
            }
        });

        resultButton.addActionListener(e -> {
            if(win)
                cardlayout.show(windowPanel, "5");
            else
                cardlayout.show(windowPanel, "6");
        });
    }

    public static String truncate(double value) {
        DecimalFormat df = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));
        return df.format(value);
    }

    public String status() {
        newConnection();
        return connectionThread.run("STATUS\n");
    }

    public void update() {
        windowPanel.revalidate();
        windowPanel.repaint();
    }

    public void updateStatus() {
        String status = status();
        switch (status) {
            case "YOURTURN":
                start = true;
                myTurn = true;
                updateMessage(gamePanel, "Messages: <br>Your turn<br>Move:" + (move + 1));
                break;
            case "NOTYOURTURN":
                start = true;
                myTurn = false;
                updateMessage(gamePanel, "Messages: <br>Not your turn");
                break;
            case "FINISHED 0":
                if(id == 0)
                    win = true;
                start = false;
                myTurn = false;
                end = true;
                updateMessage(gamePanel, "Messages: <br>Match has been finished");
                updateMatchEnd();
                break;
            case "FINISHED 1":
                if(id == 1)
                    win = true;
                start = false;
                myTurn = false;
                end = true;
                updateMessage(gamePanel, "Messages: <br>Match has been finished");
                updateMatchEnd();
                break;
            default:
                start = false;
                myTurn = false;
                end = false;
                break;
        }
    }
    public void updateMatchEnd() {
        gamePanel.remove(newCoordButton);
        gamePanel.remove(throwButton);
        resultButton.setBounds(250,500, 120,50);
        gamePanel.add(resultButton);
        update();
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

    public void updateThrown(int move, String newText) {
        JLabel newThrown = plays[move];
        int y = newThrown.getY();
        gamePanel.remove(newThrown);
        newThrown .setText("<html>" + newText + "</html>");
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

    public void reset(){
        connected = false;
        start = false;
        end = false;
        myTurn = false;
        win = false;
        move = 0;
        id = 0;
        xCoord = 0.0;
        yCoord = 0.0;

        homePanel.setLayout(null);

        startButton.setBounds(120,500, 100,50);
        homePanel.add(startButton);
        instButton.setBounds(240,500, 120,50);
        homePanel.add(instButton);
        aboutButton.setBounds(380,500, 100,50);
        homePanel.add(aboutButton);

        JLabel label = new JLabel();
        ImageIcon dbMenu = new ImageIcon("res/images/db_menu.png");
        Image dbImg = dbMenu.getImage();
        Image newImg = dbImg.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(newImg));
        label.setBounds(100,50, 400,400);
        homePanel.add(label);


        gamePanel.setLayout(null);
        throwButton.setBounds(150,500, 120,50);
        gamePanel.add(throwButton);
        newCoordButton.setBounds(300,500, 150,50);
        gamePanel.add(newCoordButton);

        JLabel label1 = new JLabel();
        ImageIcon dbGame = new ImageIcon("res/images/db_game.png");
        Image dbImg1 = dbGame.getImage();
        Image newImg1 = dbImg1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        label1.setIcon(new ImageIcon(newImg1));
        label1.setBounds(150,50, 300,300);
        gamePanel.add(label1);

        turn1.setText("<html>Turno 1:</html>");
        thrown1.setText("<html>1)</html>");
        thrown2.setText("<html>2)</html>");
        thrown3.setText("<html>3)</html>");

        turn2.setText("<html>Turno 2:</html>");
        thrown4.setText("<html>4)</html>");
        thrown5.setText("<html>5)</html>");
        thrown6.setText("<html>6)</html>");

        turn3.setText("<html>Turno 3:</html>");
        thrown7.setText("<html>7)</html>");
        thrown8.setText("<html>8)</html>");
        thrown9.setText("<html>9)</html>");
        messages.setText("<html>Messages:</html>");

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


        instPanel.setLayout(null);
        back1Button.setBounds(240,500, 120,50);

        instText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        instText.setBounds(10,5,600,200);
        instPanel.add(instText);
        instPanel.add(back1Button);


        aboutPanel.setLayout(null);
        back2Button.setBounds(240,500, 120,50);

        aboutText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        aboutText.setBounds(10,5,600,50);
        aboutPanel.add(aboutText);
        aboutPanel.add(back2Button);

        winPanel.setLayout(null);
        menuButton.setBounds(250,500, 120,50);
        winPanel.add(menuButton);

        winText.setBounds(280, 15,600,20);
        winText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        winPanel.add(winText);
        JLabel label2 = new JLabel();
        ImageIcon win0 = new ImageIcon("res/gifs/win.gif");
        Image win1 = win0.getImage();
        Image win2 = win1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        label2.setIcon(new ImageIcon(win2));
        label2.setBounds(25,40, 300,300);
        winPanel.add(label2);
        JLabel label3 = new JLabel();
        ImageIcon win3 = new ImageIcon("res/gifs/win2.gif");
        Image win4 = win3.getImage();
        Image win5 = win4.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        label3.setIcon(new ImageIcon(win5));
        label3.setBounds(275,250, 300,200);
        winPanel.add(label3);

        losePanel.setLayout(null);
        menu1Button.setBounds(250,500, 120,50);
        losePanel.add(menu1Button);

        loseText.setBounds(280, 15,600,20);
        loseText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        losePanel.add(loseText);
        JLabel label4 = new JLabel();
        ImageIcon lose = new ImageIcon("res/gifs/lose.gif");
        Image lose1 = lose.getImage();
        Image lose2 = lose1.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        label4.setIcon(new ImageIcon(lose2));
        label4.setBounds(150,50, 300,200);
        losePanel.add(label4);
        JLabel label5 = new JLabel();
        ImageIcon lose3 = new ImageIcon("res/gifs/lose2.gif");
        Image lose4 = lose3.getImage();
        Image lose5 = lose4.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        label5.setIcon(new ImageIcon(lose5));
        label5.setBounds(150,250, 300,200);
        losePanel.add(label5);
    }

    public void remove() {
        homePanel.remove(startButton);
        homePanel.remove(instButton);
        homePanel.remove(aboutButton);
        gamePanel.remove(throwButton);
        gamePanel.remove(newCoordButton);
        gamePanel.remove(turn1);
        gamePanel.remove(thrown1);
        gamePanel.remove(thrown2);
        gamePanel.remove(thrown3);
        gamePanel.remove(turn2);
        gamePanel.remove(thrown4);
        gamePanel.remove(thrown5);
        gamePanel.remove(thrown6);
        gamePanel.remove(turn3);
        gamePanel.remove(thrown7);
        gamePanel.remove(thrown8);
        gamePanel.remove(thrown9);
        gamePanel.remove(messages);
        gamePanel.remove(xText);
        gamePanel.remove(yText);
        instPanel.remove(instText);
        instPanel.remove(back1Button);
        aboutPanel.remove(aboutText);
        aboutPanel.remove(back2Button);
        winPanel.remove(menuButton);
        winPanel.remove(winText);
        losePanel.remove(menu1Button);
        losePanel.remove(loseText);
        gamePanel.remove(resultButton);
    }

}
