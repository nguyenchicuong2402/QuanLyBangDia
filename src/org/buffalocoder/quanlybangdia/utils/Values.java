package org.buffalocoder.quanlybangdia.utils;

import java.awt.*;
import java.text.SimpleDateFormat;

public class Values {
    /*========== COLOR ==========*/
    public static final Color COLOR_PRIMARY = Color.decode("#1976d2");
    public static final Color COLOR_SECONDARY = Color.decode("#4a0072");
    public static final Color COLOR_TEXT = Color.decode("#ffffff");
    public static final Color COLOR_BACKGROUND = Color.decode("#d1d9ff");
    public static final Color COLOR_ERROR = Color.decode("#ff1744");
    public static final Color COLOR_ACCEPT = Color.decode("#00e676");
    public static final Color COLOR_DARK = Color.decode("#004ba0");
    public static final Color COLOR_PRIMARY_LOGIN = Color.decode("#311b92");
    public static final Color COLOR_SELECTED = Color.decode("#81d4fa");
    public static final Color COLOR_BORDERTABLE = Color.decode("#f9fbe7");
    public static final Color COLOR_SELECTTABLE = Color.decode("#5e35b1");

    /*========= FONT =========*/
    private static final String FONT_NAME = "Times New Roman";
    public static final Font FONT_PLAIN_DEFAULT = new Font(FONT_NAME, Font.PLAIN, 18);
    public static final Font FONT_TABLE_HEADER = new Font(FONT_NAME, Font.PLAIN, 18);
    public static final Font FONT_TITLE= new Font(FONT_NAME, Font.PLAIN, 30);

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
