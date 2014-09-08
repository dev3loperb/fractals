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

        double xr, xm, cr, cm;
        double stepX = (xMax - xMin) / width;
        double stepY = (yMax - yMin) / width;
        int x, y;

        x = 0;
        for (cr = xMin; cr < xMax; cr += stepX, x++) {
            y = 0;
            for (cm = yMin; cm < yMax; cm += stepY, y++) {

                xr = 0.0;
                xm = 0.0;

                int i = 0;
                while (xr * xr + xm * xm <= 4 && i < iteration) {
                    double tempXr = xr * xr - xm * xm + cr;
                    double tempXm = 2 * xr * xm + cm;
                    xr = tempXr;
                    xm = tempXm;
                    i++;
                }

                if (xr * xr + xm * xm > 4) {
                    float col = (300f - i) / 300f;
                    col = Math.max(col, 0);
                    resultImageGraphics.setColor(new Color(col, col, col));
                    resultImageGraphics.drawLine(x, y, x, y);
                }
            }
        }


        return resultImage;
    }
}
