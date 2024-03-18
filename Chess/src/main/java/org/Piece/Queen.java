package org.Piece;

import org.Main.GamePanel;

public class Queen extends Piece{
    public Queen(int color, int col, int row){
        super(color, col, row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/piece/w-queen");
        }
        else{
            image = getImage("/piece/piece/b-queen");
        }
    }

}
