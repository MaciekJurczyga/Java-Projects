package org.Piece;

import org.Main.GamePanel;

public class Knight extends Piece{
    public Knight(int color, int col, int row){
        super(color, col, row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/piece/w-knight");
        }
        else{
            image = getImage("/piece/piece/b-knight");
        }
    }
}
