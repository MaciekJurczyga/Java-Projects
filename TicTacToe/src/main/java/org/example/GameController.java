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
    private boolean isDraw = false;
    public static ArrayList<Sign> signs = new ArrayList<>();
    char currentSign;
    private boolean signChosen;
    int[][] startCords = new int[1][2];
    int[][] endCords = new int[1][2];


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
            if(!signChosen){
                currentSign = chooseStartingSign();
                signChosen = true;
            }
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
                    if(isDraw()){
                        isDraw = true;
                        return;
                    }
                    changeTurn();
                }
            }
        } else if (!mouse.pressed) {
            mouseClicked = false;
        }
        if(isOver || isDraw){
            resetGame();
        }
    }
    private char chooseStartingSign(){
        Object[] options = {"X", "O"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose your starting sign:",
                "Starting Sign",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            return 'X';
        } else {
            return 'O';
        }
    }

    private void resetGame() {
        try{
            Thread.sleep(2000);
            int choice = JOptionPane.showConfirmDialog(null, "Would you like to play again?",
                    "Game is over", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                for (int[] ints : occupied) {
                    Arrays.fill(ints, 0);
                }
                signs.clear();
                currentSign = '?';
                isOver = false;
                isDraw = false;
                signChosen = false;
            }
            else{
                System.exit(0);
            }
        }
        catch (InterruptedException e){
            System.err.println("Error occurred" + e.getMessage());
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
        int[][][] winConditions = {
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}},
                {{0, 0}, {1, 1}, {2, 2}},
                {{0, 2}, {1, 1}, {2, 0}}
        };

        for (int[][] condition : winConditions) {
            int x1 = condition[0][0], y1 = condition[0][1];
            int x2 = condition[1][0], y2 = condition[1][1];
            int x3 = condition[2][0], y3 = condition[2][1];

            if (occupied[x1][y1] == occupied[x2][y2] && occupied[x2][y2] == occupied[x3][y3]) {
                if (occupied[x1][y1] != 0) {

                    startCords[0][0] = x1 * Board.SQUARE_SIZE  + Board.SQUARE_SIZE / 2;
                    startCords[0][1] = y1 * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2;
                    endCords[0][0] = x3 * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2;
                    endCords[0][1] = y3 * Board.SQUARE_SIZE + Board.SQUARE_SIZE / 2;
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isDraw(){
        for(int i = 0; i<occupied.length; i++){
            for(int j=0; j<occupied[0].length; j++){
                if(occupied[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
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
        if(!isOver && !isDraw) {
            if (currentSign == 'X') {
                g2.drawString("X's turn", 560, 270);
            } else if(currentSign == 'O') {
                g2.drawString("O's turn", 560, 270);
            }
        }
        if(isOver){
            g2.drawString("Player " + currentSign + " won", 560, 270);
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(7));

            int startX = startCords[0][0];
            int startY = startCords[0][1];
            int endX = endCords[0][0];
            int endY = endCords[0][1];

            g2.drawLine(startY, startX, endY, endX);
        }
        if(isDraw){
            g2.drawString("Draw!", 560, 270);
        }
    }
}
