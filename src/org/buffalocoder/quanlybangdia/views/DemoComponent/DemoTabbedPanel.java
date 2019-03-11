package org.buffalocoder.quanlybangdia.views.DemoComponent;

import org.buffalocoder.quanlybangdia.models.TabbedPanelModel;

import javax.swing.*;
import java.awt.*;

public class DemoTabbedPanel extends JFrame {
    public DemoTabbedPanel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JTabbedPane tb = new JTabbedPane(JTabbedPane.LEFT);
                tb.setUI(new TabbedPanelModel());
                tb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                tb.addTab("Hello World", new JLabel("Hello World"));
                tb.addTab("Khoa", new JLabel("Chó Khoa"));
                tb.addTab("Trường", new JLabel("Chó Trường"));

                add(tb);

                setSize(800, 600);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
    }

    public static void main(String args[]){
        new DemoTabbedPanel();
    }
}
