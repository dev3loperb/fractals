package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by igor on 05.09.14.
 */
public class DrawFractal {
    public static Image getFractalMandelbrot(double xMin, double xMax, double yMin, double yMax, int iteration, int width, int height) {
        Image resultImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics resultImageGraphics = resultImage.getGraphics();
        resultImageGraphics.setColor(Color.BLACK);
        resultImageGraphics.fillRect(0, 0, width, height);
        resultImageGraphics.setColor(Color.WHITE);

        double stepX = (xMax - xMin) / width;
        double stepY = (yMax - yMin) / width;
        int x, y;

        x = 0;
        ComplexNumber c = new ComplexNumber(0, 0);
        for (c.setX(xMin); c.real()< xMax; c.addX(stepX), x++) {
            y = 0;
            for (c.setY(yMin); c.imag() < yMax; c.addY(stepY), y++) {

                ComplexNumber z = new ComplexNumber(0, 0);
                int i = 0;
                while (z.mod() <= 2 && i < iteration) {

                    z = z.times(z).plus(c);
                    i++;
                }

                if (z.mod() > 2) {
                    float col = (200f - i) / 200f;
                    col = Math.max(col, 0);
                    resultImageGraphics.setColor(new Color(col, 0.5f, 1f));
                    resultImageGraphics.drawLine(x, y, x, y);
                }
            }
        }


        return resultImage;
    }
}
