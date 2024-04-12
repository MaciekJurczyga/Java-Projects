package org.example;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("TicTacToe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        GameController gc = new GameController();
        window.add(gc);
        window.pack();
        window.setLocationRelativeTo(null);
        gc.launchGame();

    }
}