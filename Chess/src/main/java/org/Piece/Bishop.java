package org.Piece;

import org.Main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row){
        super(color, col, row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/piece/w-bishop");
        }
        else{
            image = getImage("/piece/piece/b-bishop");
        }
    }
}
