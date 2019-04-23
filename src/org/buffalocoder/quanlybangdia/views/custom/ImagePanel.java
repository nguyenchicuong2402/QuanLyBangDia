package org.buffalocoder.quanlybangdia.views.custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private String filePath;

    public ImagePanel(String filePath) {
        setFilePath(filePath);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;

        try {
            image = ImageIO.read(new File(this.filePath));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 130, 140, this);
    }

}
