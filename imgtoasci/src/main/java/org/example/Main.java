package org.example;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static char[][] asciImg;
    public static void main(String[] args) {
        try{
            File file = new File("C:\\Users\\macie\\OneDrive\\Pulpit\\AGH_3_sem\\java_labs\\imgtoasci\\src\\main\\java\\org\\example\\40x30_39.jpg");
            BufferedImage picture = ImageIO.read(file);
            int width = picture.getWidth();
            int height = picture.getHeight();
            asciImg = new char[width][height];
            for(int n = 0; n < width; n++){
                for(int m = 0; m < height; m++){
                    int color = picture.getRGB(n, m);
                    int red = (color >>> 16) & 0xff;
                    int green = (color >> 8) & 0xff;
                    int blue = (color) & 0xff;
                    float brigthness = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
                    if(brigthness > 0.9f){
                        asciImg[n][m] = '(';
                    } else if (brigthness >0.8f) {
                        asciImg[n][m] = '$';
                    } else if (brigthness > 0.7f) {
                        asciImg[n][m] = '#';
                    } else if (brigthness > 0.6f) {
                        asciImg[n][m] = '!';
                    }else if (brigthness > 0.5f) {
                        asciImg[n][m] = '*';
                    }else if (brigthness > 0.4f) {
                        asciImg[n][m] = '0';
                    }else if (brigthness > 0.3f) {
                        asciImg[n][m] = 'x';
                    }else if (brigthness > 0.2f) {
                        asciImg[n][m] = '=';
                    }else if (brigthness > 0.1f) {
                        asciImg[n][m] = '-';
                    }
                    else if (brigthness > 0.05f) {
                        asciImg[n][m] = '.';
                }else{
                        asciImg[n][m] = ' ';
                    }
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\macie\\OneDrive\\Pulpit\\AGH_3_sem\\java_labs\\imgtoasci\\src\\main\\java\\org\\example\\obraz.txt"));
            for(int i = 0; i< width; i++){
                for(int j = 0; j<height; j++){
                   System.out.print(asciImg[i][j]);
                }
                System.out.println();
            }
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
