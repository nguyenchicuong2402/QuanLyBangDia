package org.buffalocoder.quanlybangdia.views;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private void prepareUI(){
    }

    /**
     * Constructor
     */
    public LoginForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(600, 500);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Đăng nhập");
                setVisible(true);
            }
        });
    }
}
