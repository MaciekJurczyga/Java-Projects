package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Sign {
    public BufferedImage image;
    public int col, row;
    public int x, y;

    public Sign(int row, int col){
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
    }

    public int getX(int col){
        return  col * Board.SQUARE_SIZE;
    }

    public int getY(int row){
        return  row * Board.SQUARE_SIZE;
    }


    public void draw(Graphics2D g2){
        g2.drawImage(this.image, this.x, this.y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null );
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
}
