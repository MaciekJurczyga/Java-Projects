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
    public boolean canMove(int targetCol, int targetRow){
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
          if(targetCol == preCol || targetRow == preRow){
              if(isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)){
                  return true;
              }
          }
          if(Math.abs(targetCol-preCol) == Math.abs(targetRow - preRow)){
              if(isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)){
                  return true;
              }
          }
        }
        return false;
    }

}
