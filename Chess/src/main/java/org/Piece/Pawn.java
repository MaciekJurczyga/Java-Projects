package org.Piece;

import org.Main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row){
        super(color, col, row);
        if(color == GamePanel.WHITE){
            image = getImage("/piece/piece/w-pawn");
        }
        else{
            image = getImage("/piece/piece/b-pawn");
        }
    }
}
