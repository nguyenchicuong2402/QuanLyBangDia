package org.buffalocoder.quanlybangdia.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class UIComponent {
    private JMenuBar mnubar;
    private JMenu mnuEdit, mnuFile, mnuView, mnuInsert, mnuShare, mnuHelp;
	private JButton btn;
	private JTable tbl;
    
    public JMenuBar Menu() {
    	mnubar = new JMenuBar();
    	mnubar.add(Box.createHorizontalStrut(200));
    	mnubar.setBackground(Color.decode("000051"));
//    	---
    	mnuFile = new JMenu("File");
    	mnuFile.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuFile);
//    	----
    	mnuEdit = new JMenu("Edit");
    	mnuEdit.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuEdit);
//    	---
    	mnuView = new JMenu("View");
    	mnuView.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuView);
//    	---
    	mnuInsert = new JMenu("Insert");
    	mnuInsert.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuInsert);
//    	---
    	mnuShare = new JMenu("Share");
    	mnuShare.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuShare);
//    	---
    	mnuHelp = new JMenu("Help");
    	mnuHelp.setForeground(Color.decode("#ffffff"));
    	mnubar.add(mnuHelp);
    	mnubar.setPreferredSize(new Dimension(0, 60));
    	return mnubar;
    }
    public JButton btn(String value) {
		btn = new JButton(value);
		btn.setBackground(Color.decode("#1a237e"));
		btn.setForeground(Color.decode("#ffffff"));
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(80, 40));
    	return btn;
	}
	public  JTable tbl() {
		return  tbl;
	}
}
