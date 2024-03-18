package org.Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 100000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }
    private  void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.draw(g2);
    }


}
