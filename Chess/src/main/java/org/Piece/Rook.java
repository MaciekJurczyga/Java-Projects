package org.Piece;

import org.Main.GamePanel;

public class Rook extends Piece{
    public Rook(int color, int col, int row){
        super(color, col, row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/piece/w-rook");
        }
        else{
            image = getImage("/piece/piece/b-rook");
        }
    }

}
