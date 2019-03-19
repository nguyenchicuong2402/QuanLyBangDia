package org.buffalocoder.quanlybangdia.utils;

import javax.swing.*;
import java.awt.*;

public class MaterialDesign {
    public static void materialButton(JButton btn, Dimension preferredSize){
        btn.setBackground(Values.COLOR_PRIMARY);
        btn.setForeground(Values.COLOR_TEXT);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setPreferredSize(preferredSize);
    }

    public static void materialTextField(JTextField textField, Dimension preferredSize){
        textField.setFont(Values.FONT_PLAIN_DEFAULT);
        textField.setBackground(Values.COLOR_BACKGROUND);
        textField.setPreferredSize(preferredSize);
    }
}
