package org.example;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static char[][] asciImg;
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\macie\\Java-Projects\\imgtoasci\\src\\main\\java\\org\\example\\obraz.jpg");
            BufferedImage originalPicture = ImageIO.read(file);


            int asciiWidth = 120;
            int asciiHeight = (int) Math.ceil((double) originalPicture.getHeight() / (originalPicture.getWidth() / (double) asciiWidth));


            BufferedImage resizedPicture = new BufferedImage(asciiWidth, asciiHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedPicture.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalPicture, 0, 0, asciiWidth, asciiHeight, null);
            g.dispose();

            int width = resizedPicture.getWidth();
            int height = resizedPicture.getHeight();
            asciImg = new char[width][height];


            String asciiChars = "~%#*+=-:. ";

            for(int n = 0; n < width; n++) {
                for(int m = 0; m < height; m++) {
                    int color = resizedPicture.getRGB(n, m);
                    int red = (color >>> 16) & 0xff;
                    int green = (color >> 8) & 0xff;
                    int blue = (color) & 0xff;
                    float brightness = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;


                    int index = (int) (brightness * (asciiChars.length() - 1));
                    asciImg[n][m] = asciiChars.charAt(index);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\macie\\Java-Projects\\imgtoasci\\src\\main\\java\\org\\example\\obraz.txt"));
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    writer.write(asciImg[j][i]); // Zamieniamy kolejność indeksów, aby dostosować do wyświetlania w pliku tekstowym
                }
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
