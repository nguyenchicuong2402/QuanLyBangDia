package org.buffalocoder.quanlybangdia.views.custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private BufferedImage image;
    private String filePath;

    public ImagePanel(String filePath) {
        setFilePath(filePath);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            image = ImageIO.read(new File(this.filePath));

            // resize hình ảnh
            Image image_resize = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

            g.drawImage(image_resize, 20, 0, this);
        } catch (IOException ex) {
        }
    }

}
