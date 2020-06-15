package com.uni.redes.UI;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Window extends JFrame {
    CardLayout cardlayout = new CardLayout();

    public Window() {
        super("Crazy darts");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(cardlayout);
    }
}

