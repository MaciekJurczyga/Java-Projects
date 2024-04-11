package org.example;

import java.awt.*;

public class Board {
    final int MAX_ROW = 3;
    final int MAX_COL = 3;
    public static final int SQUARE_SIZE = 180;

    public void draw(Graphics2D g2){
        float[] dash1 = {15.0f, 15.0f};
        BasicStroke dashed = new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

        for (int i = 1; i < MAX_ROW; i++) {
            g2.setStroke(dashed);
            g2.drawLine(0, i * SQUARE_SIZE, MAX_COL * SQUARE_SIZE, i * SQUARE_SIZE);
        }

        for (int i = 1; i < MAX_COL; i++) {
            g2.setStroke(dashed);
            g2.drawLine(i * SQUARE_SIZE, 0, i * SQUARE_SIZE, MAX_ROW * SQUARE_SIZE);
        }


    }
}
