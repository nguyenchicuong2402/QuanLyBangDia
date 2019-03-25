package org.buffalocoder.quanlybangdia.utils;

import javax.swing.*;
import java.awt.*;

public class MaterialDesign {
    public static void materialButton(JButton button){
        button.setBackground(Values.COLOR_PRIMARY);
        button.setForeground(Values.COLOR_TEXT);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    public static void materialTextField(JTextField textField){
        textField.setFont(Values.FONT_PLAIN_DEFAULT);
        textField.setBackground(Values.COLOR_BACKGROUND);
        textField.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 0, Values.COLOR_PRIMARY));
    }

    public static void materialTable(JTable table){
        table.setSelectionBackground(Values.COLOR_SECONDARY);
        table.setSelectionForeground(Values.COLOR_TEXT);
        table.getTableHeader().setBackground(Values.COLOR_PRIMARY);
        table.getTableHeader().setForeground(Values.COLOR_TEXT);
        table.getTableHeader().setFont(Values.FONT_TABLE_HEADER);
        table.setFont(Values.FONT_PLAIN_DEFAULT);
        table.setRowHeight(45);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        // không cho người dùng thay đổi cột
        table.getTableHeader().setReorderingAllowed(false);
    }
}
