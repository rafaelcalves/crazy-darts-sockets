package com.uni.redes.UI;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Menu extends JFrame {
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
    JButton back3Button = new JButton("Back");
    CardLayout cardlayout = new CardLayout();

    JLabel instText = new JLabel("<html>Welcome to Crazy Darts Sockets. <br>" +
            "<br>" +
            "Two players throw their darts by entering x and y, each player has three moves.<br>" +
            "Blablabla </html>");

    JLabel aboutText = new JLabel("<html>This is a computer network work and it was developed by Fábio Krein and Rafael Côrrea. Blablabla</html>");

    int xCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);
    int yCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);;
    JLabel xText = new JLabel("<html>X: " + xCoord + "</html>");
    JLabel yText = new JLabel("<html>Y: " + yCoord + "</html>");

    public Menu() {
        super("Crazy darts");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(cardlayout);

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
        back1Button.setBounds(150,500, 120,50);
        newCoordButton.setBounds(300,500, 150,50);

        JLabel label1 = new JLabel();
        ImageIcon dbGame = new ImageIcon("res/images/db_game.png");
        Image dbImg1 = dbGame.getImage();
        Image newImg1 = dbImg1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        label1.setIcon(new ImageIcon(newImg1));
        label1.setBounds(150,50, 300,300);
        gamePanel.add(label1);
        xText.setBounds(280,405,600,20);
        xText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(xText);
        yText.setBounds(280,435,600,20);
        yText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        gamePanel.add(yText);
        gamePanel.add(newCoordButton);
        gamePanel.add(back1Button);


        instPanel.setLayout(null);
        back2Button.setBounds(240,500, 120,50);

        instText.setBounds(10,5,600,100);
        instPanel.add(instText);
        instPanel.add(back2Button);


        aboutPanel.setLayout(null);
        back3Button.setBounds(240,500, 120,50);

        aboutText.setBounds(10,5,600,50);
        aboutPanel.add(aboutText);
        aboutPanel.add(back3Button);


        windowPanel.add(homePanel,"1");
        windowPanel.add(gamePanel,"2");
        windowPanel.add(instPanel,"3");
        windowPanel.add(aboutPanel,"4");

        cardlayout.show(windowPanel, "1");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.remove(xText);
                gamePanel.remove(yText);
                xCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);
                yCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);;
                xText = new JLabel("<html>X: " + xCoord + "</html>");
                yText = new JLabel("<html>Y: " + yCoord + "</html>");
                xText.setBounds(280,405,600,20);
                xText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                gamePanel.add(xText);
                yText.setBounds(280,435,600,20);
                yText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                gamePanel.add(yText);
                windowPanel.revalidate();
                windowPanel.repaint();
                cardlayout.show(windowPanel, "2");
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
                gamePanel.remove(xText);
                gamePanel.remove(yText);
                xCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);
                yCoord = (int)((Math.random() * ((10 - 0) + 1)) + 0);;
                xText = new JLabel("<html>X: " + xCoord + "</html>");
                yText = new JLabel("<html>Y: " + yCoord + "</html>");
                xText.setBounds(280,405,600,20);
                xText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                gamePanel.add(xText);
                yText.setBounds(280,435,600,20);
                yText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                gamePanel.add(yText);
                windowPanel.revalidate();
                windowPanel.repaint();
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

        back3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardlayout.show(windowPanel, "1");
            }
        });

        add(windowPanel);
        setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
        setVisible(true);
    }
}
