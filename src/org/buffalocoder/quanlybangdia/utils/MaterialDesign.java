package org.buffalocoder.quanlybangdia.utils;

import javax.swing.*;
import java.awt.*;

public class MaterialDesign {
    public static void materialButton(JButton button){
        button.setBackground(Values.COLOR_PRIMARY);
        button.setForeground(Values.COLOR_TEXT);
        button.setFont(Fonts.DEFAULT);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    public static void materialTextField(JTextField textField){
        textField.setFont(Values.FONT_PLAIN_DEFAULT);
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createMatteBorder(0, 1
                , 2, 0, Values.COLOR_PRIMARY));
    }

    public static void materialTextArea(JTextArea textArea){
        textArea.setFont(Values.FONT_PLAIN_DEFAULT);
        textArea.setBackground(Values.COLOR_BACKGROUND);
        textArea.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0, Values.COLOR_PRIMARY));
    }

    public static void materialTable(JTable table){
        table.setSelectionBackground(Values.COLOR_DARK);
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBackground(Values.COLOR_TEXT);
        table.getTableHeader().setFont(Fonts.TABLE_HEADER);

        table.setFont(Fonts.DEFAULT);
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public static void materialLabel (JLabel label){
        label.setFont(Fonts.DEFAULT);
        label.setForeground(Color.BLACK);
    }

    public static void materialCheckBox(JCheckBox checkBox){
        checkBox.setFont(Fonts.DEFAULT);
    }

    public static void materialComboBox (JComboBox comboBox){
    }
}
