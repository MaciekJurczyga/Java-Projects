package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController extends JPanel implements Runnable  {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 540;
    public final int FPS = 60;
    public static int[][] occupied = new int[3][3];
    Board board = new Board();
    Thread gameThread;
    Mouse mouse = new Mouse();
    private boolean mouseClicked = false;
    public static ArrayList<Sign> signs = new ArrayList<>();
    char currentSign = 'O';

    public GameController(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.white);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run(){
        double drawInterval = 100000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            lastTime = currentTime;
        }
    }
    private void update(){
        if(mouse.pressed && !mouseClicked && mouse.x > 0 && mouse.x < 540 && mouse.y > 0 && mouse.y < 540 ){
            int col = getCol(mouse.x);
            int row = getRow(mouse.y);

            synchronized (signs) {
                if (occupied[row][col] == 0) {
                    if(currentSign == 'X'){
                        signs.add(new XSign(row, col));
                    } else {
                        signs.add(new OSign(row, col));
                    }
                    occupied[row][col] = currentSign;
                    mouseClicked = true;
                    changeTurn();
                }
            }
        } else if (!mouse.pressed) {
            mouseClicked = false;
        }
    }
    public void changeTurn(){
        if(currentSign == 'X'){
            currentSign = 'O';
        }
        else{
            currentSign = 'X';
        }
    }
    public int getCol(int x){
        return x/Board.SQUARE_SIZE;
    }

    public int getRow(int y){
        return  y/Board.SQUARE_SIZE;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        board.draw(g2);

        synchronized (signs){
            for (Sign s : signs) {
                s.draw(g2);
            }
        }
    }
}
