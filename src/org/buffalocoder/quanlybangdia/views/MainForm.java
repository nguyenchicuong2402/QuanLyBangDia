package org.buffalocoder.quanlybangdia.views;

import java.awt.BorderLayout;

import javax.swing.*;

import org.buffalocoder.quanlybangdia.components.UIComponent;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    final int WIDTH = 1200;
    final int HEIGHT = 600;
    final String TITLE = "Quản lý băng đĩa";
    //========= UI ===========//
    private JPanel mainPanel, menuPanel, contentPanel;
    private JTabbedPane menuTabbed;
    private JTextField txtSearch;
    
    

    /**
     * Tạo GUI
     */
    private void prepareUI(){
    	this.setContentPane(mainPanel = new JPanel(new BorderLayout()));
//    				header
    	UIComponent mnbar = new UIComponent();
    	setJMenuBar(mnbar.Menu());
//    	            content
        contentPanel = new JPanel();
        contentPanel.add(mnbar.btn("Thêm"));
        contentPanel.add(mnbar.btn("Sửa"));
        contentPanel.add(mnbar.btn("Xóa"));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

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
                setLocation(300, 100);
                setTitle(TITLE);
                setUndecorated(true);
                setVisible(true);
            }
        });
    }
}
