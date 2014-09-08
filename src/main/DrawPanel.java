package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by igor on 05.09.14.
 */
public class DrawPanel extends JPanel {

    protected static final int width = 600;
    protected static final int height = 600;
    protected int xMouse = -1, yMouse = -1;
    protected int xEndMouse = -1, yEndMouse = -1;
    protected double xMin;
    protected double xMax;
    protected double yMin;
    protected double yMax;
    protected Image image;
    protected final int iteration = 1000;


    public DrawPanel() {
        setPreferredSize(new Dimension(width, height));
        initMouseListeners();
    }

    public void reset() {
        xMin = -2;
        xMax = 1;
        yMin = -1;
        yMax = 1;
        draw(getFractalMandelbrot());
    }

    protected void initMouseListeners() {
        //TODO create classes listeners
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    reset();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    xMouse = e.getX();
                    yMouse = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //TODO обработку в отдельный метод
                double xDelta = (xMax - xMin) / getWidth();
                xMax = xDelta * e.getX() + xMin;
                xMin = xDelta * xMouse + xMin;
                double yDelta = (yMax - yMin) / getHeight();
                yMax = yDelta * e.getY() + yMin;
                yMin = yDelta * yMouse + yMin;
                //TODO вынести в отдельную функцию
                if (xMin > xMax) {
                    double temp = xMin;
                    xMin = xMax;
                    xMax = temp;
                }
                if (yMin > yMax) {
                    double temp = yMin;
                    yMin = yMax;
                    yMax = temp;
                }

                draw(getFractalMandelbrot());

                xMouse = -1;
                yMouse = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xEndMouse = e.getX();
                yEndMouse = e.getY();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            //TODO вынести копирование изображения в отдельный метод
            Image imageCopy = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
            Graphics graphics = imageCopy.getGraphics();
            graphics.drawImage(image, 0, 0, null);

            if (xMouse != -1) {
                graphics.setColor(Color.RED);
                drawRect(graphics, xMouse, xEndMouse, yMouse, yEndMouse);
            }

            g.drawImage(imageCopy, 0, 0, imageCopy.getWidth(null), imageCopy.getHeight(null), null);
            g.dispose();
        }
    }

    protected void drawRect(Graphics g, int x1, int x2, int y1, int y2) {
        int x, y, width, height;
        x = Math.min(x1, x2);
        width = Math.max(x1, x2) - x;
        y = Math.min(y1, y2);
        height = Math.max(y1, y2) - y;
        g.drawRect(x, y, width, height);
    }

    public void draw(Image image) {
        this.image = image;
        repaint();
    }

    protected Image getFractalMandelbrot() {
        return DrawFractal.getFractalMandelbrot(xMin, xMax, yMin, yMax, iteration, getWidth(), getHeight());
    }

}
