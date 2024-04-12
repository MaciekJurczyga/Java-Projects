package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController extends JPanel implements Runnable  {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 540;
    public final int FPS = 60;
    public static int[][] occupied = new int[3][3];
    Board board = new Board();
    Thread gameThread;
    Mouse mouse = new Mouse();
    private boolean mouseClicked = false;
    private boolean isOver = false;
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
        if(!isOver && mouse.pressed && !mouseClicked && mouse.x > 0 && mouse.x < 540 && mouse.y > 0 && mouse.y < 540 ){
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
                    if(isOver()){
                        isOver = true;

                        return;
                    }
                    changeTurn();
                }
            }
        } else if (!mouse.pressed) {
            mouseClicked = false;
        }
        if(isOver){
            resetGame();
        }
    }

    private void resetGame() {
        try{
            Thread.sleep(2000);
            int choice = JOptionPane.showConfirmDialog(null, "Game is over",
                    "would you like to play again?", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                for (int[] ints : occupied) {
                    Arrays.fill(ints, 0);
                }
                signs.clear();
                // currentSign = '0';
                isOver = false;
            }
            else{
                System.exit(0);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
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

    public boolean isOver(){
        if(occupied[0][0] == occupied[0][1] && occupied[0][2] == occupied[0][1]){
            if(occupied[0][0] != 0) {
                return true;
            }
        }
        if(occupied[1][0] == occupied[1][1] && occupied[1][2] == occupied[1][1]){
            if(occupied[1][0] != 0) {
                return true;
            }
        }
        if(occupied[2][0] == occupied[2][1] && occupied[2][2] == occupied[2][1]){
            if(occupied[2][0] != 0) {
                return true;
            }
        }
        if(occupied[0][0] == occupied[1][0] && occupied[2][0] == occupied[1][0]){
            if(occupied[0][0] != 0) {
                return true;
            }
        }
        if(occupied[0][1] == occupied[1][1] && occupied[2][1] == occupied[1][1]){
            if(occupied[0][1] != 0) {
                return true;
            }
        }
        if(occupied[0][2] == occupied[1][2] && occupied[2][2] == occupied[1][2]){
            if(occupied[0][2] != 0) {
                return true;
            }
        }
        if(occupied[0][0] == occupied[1][1] && occupied[1][1] == occupied[2][2]){
            if(occupied[0][0] != 0) {
                return true;
            }
        }
        if(occupied[0][2] == occupied[1][1] && occupied[1][1] == occupied[2][0]){
            if(occupied[0][2] != 0) {
                return true;
            }
        }

        return false;
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
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua", Font.PLAIN, 30));
        g2.setColor(Color.black);
        if(!isOver) {
            if (currentSign == 'X') {
                g2.drawString("X's turn", 560, 270);
            } else {
                g2.drawString("O's turn", 560, 270);
            }
        }
        if(isOver){
            g2.drawString("Player " + currentSign + " won", 560, 270);
        }
    }
}
