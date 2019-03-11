package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.models.TabbedPanelModel;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LoginForm extends JFrame {

    private void prepareUI(){
        this.setLayout(new BorderLayout());

        JTabbedPane tb = new JTabbedPane(JTabbedPane.LEFT);
        tb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tb.setUI(new TabbedPanelModel());
        tb.add("Hello", new QuanLyNhanVienTabbed());
        tb.add("Tab2", new JTextArea(""));
        tb.add("Tab3", new JTextArea(""));
        tb.add("Tab4", new JTextArea(""));
        tb.add("Tab5", new JTextArea(""));
        this.add(tb, BorderLayout.CENTER);
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
