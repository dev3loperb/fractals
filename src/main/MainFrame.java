package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by igor on 02.09.14.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Draw Fractal");
        DrawPanel drawPanel = new DrawPanel();
        this.setContentPane(drawPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        drawPanel.reset();
    }
}
