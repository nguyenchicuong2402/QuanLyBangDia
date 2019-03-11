package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final String TITLE = "Quản lý băng đĩa";


    /**
     * Tạo GUI
     */
    private void prepareUI(){

    }

    /**
     * Constructor empty
     */
    public MainForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(WIDTH, HEIGHT);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle(TITLE);
                setVisible(true);
            }
        });
    }
}
