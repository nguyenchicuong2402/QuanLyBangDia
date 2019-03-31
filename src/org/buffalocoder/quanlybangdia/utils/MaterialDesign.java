package org.buffalocoder.quanlybangdia.utils;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MaterialDesign {
    public static Border BORDER_ERROR = BorderFactory.createLineBorder(Colors.ERROR, 3);
    public static Border BORDER_DIALOG = BorderFactory.createLineBorder(Colors.PRIMARY, 2);
    public static Border BORDER_COMBOBOX = BorderFactory.createLineBorder(Colors.PRIMARY, 2);

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
