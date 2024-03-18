package org.Piece;

import org.Main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Piece {
    public BufferedImage image;
    public int x,y;
    public int col, row, preCol, preRow;
    public int color;



    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }
    public BufferedImage getImage(String path){
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(path + ".png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public int getX(int col){
        return col* Board.SQUARE_SIZE;
    }
    public int getY(int col){
        return col* Board.SQUARE_SIZE;
    }
}
