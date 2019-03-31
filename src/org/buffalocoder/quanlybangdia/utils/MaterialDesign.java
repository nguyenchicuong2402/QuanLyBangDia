package org.buffalocoder.quanlybangdia.utils;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MaterialDesign {
    private static final String FONT_NAME = "Times New Roman";

    //========== BORDER =========//
    public static Border BORDER_ERROR = BorderFactory.createLineBorder(Colors.ERROR, 3);
    public static Border BORDER_DIALOG = BorderFactory.createLineBorder(Colors.DARK, 2);

    //========== FONT ==========//
    public static final Font FONT_DEFAULT            = new Font(FONT_NAME, Font.PLAIN, 18);
    public static final Font FONT_SMALL              = new Font(FONT_NAME, Font.PLAIN, 16);
    public static final Font FONT_SMALL_ITALIC       = new Font(FONT_NAME, Font.ITALIC, 16);
    public static final Font FONT_TABLE_HEADER       = new Font(FONT_NAME, Font.PLAIN, 18);
    public static final Font FONT_TITLE_1            = new Font(FONT_NAME, Font.PLAIN, 40);
    public static final Font FONT_TITLE_2            = new Font(FONT_NAME, Font.PLAIN, 30);
    public static final Font FONT_TITLE_ALERT        = new Font(FONT_NAME, Font.PLAIN, 20);

    //========== COLOR ==========//
    public static final Color COLOR_PRIMARY         = Color.decode("#1976d2");
    public static final Color COLOR_SECONDARY       = Color.decode("#2196F3");
    public static final Color COLOR_TEXT            = Color.decode("#ffffff");
    public static final Color COLOR_BACKGROUND      = Color.decode("#E3F2FD");
    public static final Color COLOR_ERROR           = Color.decode("#ff1744");
    public static final Color COLOR_ACCEPT          = Color.decode("#00e676");
    public static final Color COLOR_DARK            = Color.decode("#0D47A1");
    public static final Color COLOR_CARD            = Color.decode("#ffffff");

    public static void materialButton(JButton button){
        button.setBackground(Colors.PRIMARY);
        button.setForeground(Colors.TEXT);
        button.setFont(Fonts.DEFAULT);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    public static void materialTextField(JTextField textField){
        textField.setFont(Fonts.DEFAULT);
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createMatteBorder(0, 1
                , 2, 0, Colors.PRIMARY));
    }

    public static void materialTextArea(JTextArea textArea){
        textArea.setFont(Fonts.DEFAULT);
        textArea.setForeground(Color.BLACK);
        textArea.setBorder(BorderFactory.createMatteBorder(0, 1
                , 2, 0, Colors.PRIMARY));
    }

    public static void materialTable(JTable table){
        table.setSelectionBackground(Colors.DARK);
        table.setSelectionForeground(Color.WHITE);

        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBackground(Colors.TEXT);
        table.getTableHeader().setFont(Fonts.TABLE_HEADER);

        table.setFont(Fonts.DEFAULT);
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(false);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public static void materialScrollPane (JScrollPane scrollPane){
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Colors.BACKGROUND);
    }

    public static void materialLabel (JLabel label){
        label.setFont(Fonts.DEFAULT);
        label.setForeground(Color.BLACK);
    }

    public static void materialCheckBox(JCheckBox checkBox){
        checkBox.setFont(Fonts.DEFAULT);
        checkBox.setBackground(Colors.BACKGROUND);
    }

    public static void materialComboBox (JComboBox comboBox){
        comboBox.setBackground(Colors.BACKGROUND);
        comboBox.setFont(Fonts.DEFAULT);
    }

    public static void materialPanel (JPanel panel){
        panel.setBackground(Colors.BACKGROUND);
    }

    public static void materialDateChooser (JDateChooser dateChooser){
        dateChooser.setFont(Fonts.DEFAULT);
        dateChooser.setForeground(Color.BLACK);
    }
}
