package com.Snakes.main;

import javax.swing.*;

/**
 * @author PlagueWZK
 * description: Main
 * date: 2024/8/27 19:25
 */

public class Main extends JFrame {

    public static int BoundOfX = 900;
    public static int BoundOfY = 750;
    public MainPanel mainPanel;
    public static void main(String[] args) {
        new Main().init();
    }

    public void init(){
        this.setTitle("牢G大战怂军郝");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(BoundOfX,BoundOfY);
        this.setResizable(true);

        mainPanel = new MainPanel();
        this.add(mainPanel);
        this.addKeyListener(mainPanel);
    }
}
